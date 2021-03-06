import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.ameet.kafkasample.Util;
import org.ameet.kafkasample.model.KafkaMessage;
import org.ameet.kafkasample.model.ihg.EZMessage;
import org.ameet.kafkasample.model.ihg.json.Reservation;
import org.ameet.kafkasample.model.ihg.reservation.EnvelopeType;
import org.ameet.kafkasample.model.ihg.reservation.ObjectFactory;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * Created by ameet.chaubal on 7/14/2017.
 */
public class TestEZProcessor {
    private static final String MODIFY_RESERV_REQ_XML_FILE = "ModifyreservationRequest.xml";
    private static final String CF_NUMBER_FIELD_NAME = "cfNumber";
    private static ObjectMapper mapper = new ObjectMapper();

    public static boolean isZipped(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream
                .GZIP_MAGIC >> 8));
    }

    @Test
    public void testUnzip() throws IOException {
        String text = "eAHdWN9To8oS/lemeDkvrhtiXDVvSDDhSiALxKx1zj4gmSRUEcgdwGtqy//9fj0DmGhyLPfsfdj7oBVmenq6e7q//vFD2" +
                "+RJVnqLIEq51v+hrfKSp0FeiRifmhE6N752oj3ybJ4LM5/T4mQcWEG7GG43vND6f+rfT7R4FWUZT1s6EK15KZK4aJfoYE0WVA" +
                "/tMlZLET3y1FjyLN7a2SInaSJ8lXsfQZKRDJ1OR8cZuT+oym3NKJg2i3aWlEmUQjLNMLTn5+cTTfB/V7woc/F" +
                "/rqd8Qp8XXDxGZZJnpG5cCUGGhTmmwQBGErwIyqisyEDjeyykUVFON/Oo5AP8hcmazNzt6BefOhefunqon/d75" +
                "/3u5UHSEXmNk8dReuCg3h5M1niw9lozX6+TsuRzsIwFP3yz3gn1s373qn92BbKkmETJ" +
                "/Ho7IaeF6Au8MJfKNMracyxD34VbrR84nlrrXV519d5ll47T/fZc6" +
                "+sn2mZdmC9EeqdzeX5xTpI0UgXkLlnMG0ZduFACR84WiVhD6H4pKtwtrS2vXEVJVvuhPRqC1YOIsnm9IiMmVgHURNVG5Bsuyq0bSWNj2XBDgwXeNBzNrCBkn9iNYfvXU9/VcPlS5NUG9yNQ4lLeqNi5joPLMsXEzUW54iJjDkybchbyaI3dOS9ikWyUO/z5Qyv5E1hoZhC67NYd4t/smv5Z9G/ksvCri3/0Gc6w++3WwT/6/Da7df/Kfu6c9gyMiAQ31nmF55OmnCPAEaftU1bSBa+30qMSLrwshdOSqSmC4SOTNMrwxNChtqY9ND3CqCMqHjQICZLm2XLwe5iFxBV5vm6wttWdngmqr6OnZF2tvTiuNpGM8t5Re4Qzj32dWnjKa2sQMNdzg7F3a7tD+TpwyXkVlybQgJChQ1HzN4a68S2LjezhiAUTyxow2w0t37VCZpimFQTMcAfMYAPDdu7ZNDBY6A2Me+Zas2BiTCwf3+zWsibs3puy6WRghOBBv03DZTMjNEdsdO0xD144sthZF/yxFN4xz2czz79lRih3BlZwy2Z2OMKFzPKHnuuNbZOZI0QP7p+MPNdS+1JgxzMNB3c4TiCPh749cSwWjCyLBNixjuUGU9+SIhnMR0jeTB0WhMb9KdjiCt+bGXeWVPPGtwdD/AS54QQem/jenT2wCGvhscsKyAOD8gxmbp4TSLhVrlxss3gl8gZqAE+JQqmijERJgIyzL2AMnkjIr5cJvUCP0EnnXNQwB6hACmgx16X8u4m26528Wn+Sc2n9Hjyn3S9BhYuHVQQkKzknFCI/9CFQLfgH5asxTNYRCrcav65xsvboJtLr1SbGMwnq3sKHEBAAdiLCnxOFTlK2mqJSgJKDaAvTkDQTFCxJtlT20CZcsKrg2KtAOK7SMtmkACZ5+0OEHapTJKKBy9XVaacD2vyRC5HMuZ21iSIXyTLJovQaZ37egOqma77IBQ+jp/ZKeBVCPkrSbY2uJJb8DvOS8LUR8u1RYLIU31iUXCieut47/dKDHqSgYtjehEotehpSLtpBYR1uo7iA7lzZAGQyG6M0fD5pIEu/2CHUO+qWlrKjYgMvQ+7baVQik+2A2y9TpeFvPSGnvnNBRyn1xlT1+p6h5Bpqzu+1fw74IqF6NK8zFwV3Rwbrh4K7NbDyMmJfHn/cs6svR0TunV+enl+8ft2WXvKEH1BcaV+6RApDLal2NmXilu8OPPtaRWmy2CJWVJjq0CkmCsQGCVdseIzcTs4u40ALZI5pZd6zWXu9dFp1hXTLQw67d7KJOWWeOmwNBCBBLnme2vg5PqNkuYLiKjlG2+gh5XVAaa0Z3/JvlVFbO5HVHtqPVZQzH4X63zK44UhS7t80opuI24+O9xGxW6NEC3RHIbEn401yVOBJkAiryaB5Y7XfNVYOBcTfRNEBRPqf2/xV7qsjunmJtxkDRdMuOLyTLt4q23gIAXrjXs3aO/n2heydfPuaUKXbPTCVJNBygcLRwaxF1VZ1u6h32MREye2j6HWYMbTckJneeGwHge1Rj9o2li4KYCtEA+A43gw1tfXNtCYhu0HhPpyiLGYDb4hs8HIAG4Hl39kmVdP2GCX0KXoTdAeua+H3LunYBsF0zMxb9AMkBbMD1tVPmSzu0TwPPDSEO7z9a4buY6BTXXLK0OpLLV46HzbyQsvZu2PioytWLQPdgTYGfFHT23e2Yw0tEghYECOh52lCQ5M5laQos7dtqd1WzXNJK/OnsmZd26P3L/JMNf+A/0rULfzMcNBFIT0vE8zd6uHAvzzrHmMsXIrJzV6F36bTtz7VpqFXztkmoePp6UDIwSvqyd1E8AXqWygNzVEkNEmyJZDhgcoZ+pmrSGDoksVpNaepCTwLMxtoByw8Uhepxp4qxSM9PTqw0GKh8Y1dnF6ck0vSC5l2eC8X2RkeGqsqaatqflzPLrmYqBYXEmzU1o0cDKrhGGR2kfFL7D5GabVf3KNclSOiUnZK1BnE1CDBLknxomA9k/pYOidvrd2i1v54Bz+0PH9oY06krOAbbjDx8BshyG7Qj1Nj7aInh7/il+95419tiKbAV3a4SSOy16sR4y+xCmW+dT5PFkksR5lwpmxZY9ImKldwp89yALcz7vz8MtpEH4YmRas2mIXSO/1Aa3vk0IHx5z86LedXLxPRD/CC+LvDzA+c3B8QfuAgtcJqrvbq0PcT7T+RyFDhK+SqfbODQn/Ni0LNNDwAmXwdptpzdsZWUcEeOM/YPCniSCDwWZ+VYsvKnAm+RnfMsjxj/CkpSjBn9CyMp5zGEuyPA09ag2uBXvJzO55Y5M1vBbp/nMINV2W5aTC420EL2Xae70jd++VS53LeelCq78//BZkfA7I=";

        byte[] decoded = Base64.getDecoder().decode(text);

        ByteArrayInputStream bais = new ByteArrayInputStream(decoded);
        InflaterInputStream iis = new InflaterInputStream(bais);

        String result = "";
        byte[] buf = new byte[5];
        int rlen = -1;
        while ((rlen = iis.read(buf)) != -1) {
            result += new String(Arrays.copyOf(buf, rlen));
        }
        System.out.println(result);


        JsonNode cfNumber = null;
        JsonNode node = mapper.readTree(result);


        JsonNode cfNode = searchForEntity(node, "cfNumber");
        System.out.println("DFS:" + cfNode.asText());

    }

    private JsonNode searchForEntity(JsonNode node, String entityName) {

        if (node == null) {
            return null;
        }
        System.out.println("node:" + node);
        node.fieldNames().forEachRemaining(s -> System.out.println("field:" + s));
        if (node.has(entityName)) {
            System.out.println("!!! found");
            return node.get(entityName);
        }
        if (!node.isContainerNode()) {
            return null;
        }
        for (JsonNode child : node) {
            System.out.println("Iterate:" + child);
            if (child.isContainerNode()) {
                JsonNode childResult = searchForEntity(child, entityName);
                if (childResult != null && !childResult.isMissingNode()) {
                    return childResult;
                }
            }
        }
        return null;
    }

    @Test
    public void testEzConvert() throws IOException {
        URL url = Resources.getResource("sample.json");
        String text = Resources.toString(url, Charsets.UTF_8);

        EZMessage ezMessage = mapper.readValue(text, EZMessage.class);
        System.out.println(ezMessage.getAction());
    }

    @Test
    public void testReservationJson() throws IOException {
        String text = Util.fileToString("Reservation.json");

        Reservation reservation = mapper.readValue(text, Reservation.class);
        System.out.println("HotelCode: " + reservation.getHotelReservation().getHotel().getCode());
        reservation.getHotelReservation().getGuests().forEach(guest -> System.out.println(guest.getPersonName()
                .getGivenName() + ":" + guest.getPersonName().getSurname()));
        Pattern pattern = Pattern.compile("(?=.*\\{.*)(?=hotelReservation)", Pattern.DOTALL);
        if (pattern.matcher(text).find()) {
            System.out.println("YESSSS!!");
        }
    }

    @Test
    public void testMetadata() {
        String text = Util.fileToString("messageMetadata.json");
        KafkaMessage kafkaMessage = null;
        try {
            kafkaMessage = mapper.readValue(text, KafkaMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(kafkaMessage.getMessageMetadata().getHotelCodes());
        kafkaMessage.getMessageMetadata().setHotelCode(kafkaMessage.getMessageMetadata().getHotelCodes().toString());
        System.out.println("hotel codes======>"+kafkaMessage.getMessageMetadata().getHotelCode());
    }

    @Test
    public void testXMLModifyRequest() throws IOException, JAXBException {
        URL url = Resources.getResource(MODIFY_RESERV_REQ_XML_FILE);
        String text = Resources.toString(url, Charsets.UTF_8);


        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        @SuppressWarnings("unchecked")
        EnvelopeType envelopeType = ((JAXBElement<EnvelopeType>) jaxbContext.createUnmarshaller().unmarshal
                (new StringReader(text))).getValue();


//        JAXBContext jaxbContext = JAXBContext.newInstance(EnvelopeType.class);
//        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//        EnvelopeType envelopeType = (EnvelopeType) jaxbUnmarshaller.unmarshal(new StringReader(text));
        System.out.println(envelopeType.getHdr().getMsgID());
    }
}
