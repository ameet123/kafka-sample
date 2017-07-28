import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.ameet.kafkasample.model.ihg.EZMessage;
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
        String text = "eAFVU9tuozAQfa/Uf7D8zgIpuRAZVwSySdRcWiAX7RuLnRRtgKxxqvL3OzY02SIBM2fmnLlgyPNncUYfXNR5VXrY" +
                "/mFhxMusYnl58vA2+WmM8DN9fCDT8oOfqwunZM4EJRuRn+KmptNJTMwvR6PBe1rS" +
                "/XSyn7QB7euIf7n8H1CuxpO84LRn2UPDGhq2m9j9sdUf992x7fQMyxlbVquk80jIa6k15" +
                "+GBmDdP40ryBmt9P5MwGJ3Lc8Tr6I2YHUBW9WnHBYWBidnZCluElL1wURqDzOb9bOgY2eDoGs4QWhuNjtAfOw7coTNiQ2ugmcBQxFXFOI2bMtOg9tTOaALtElNZ5DVtzlXKkoYeVkti3l1i6p12AGy46xYom5iSWGQo+ltLsQg9rDeLkX+SoQyYh18jjEwKaiqzJVICs8YyldeaBlVR5NDAHSEQaRA8hPTwfesYTUuQ+wJ6NtRg1zPkgBW852cIwtmYVNUfVTaIk9liFmygelQEZZsGu1UxP9YtQTMIboXs/TjeaXAmORwh9YRpUZAKpuI7YCh7/Vt42LG/X9DZ5yWECpZrj7QIUJMGJY2izg7t/J1wLWuQryVapwX38MqPXjCKr6J19/5yOdlGs46ik33GBHDUCyZmy9L28BOKfOg3yGUD4yRLf534IAMrBc0ZmL/yC2RZT7YLWdW1lAIStzFEQCIBG/YC1rpoW0DfC6taUNL8+lydEb2p79geErBuf9zjwz/gfhre";

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
        System.out.println("node:"+node);
        node.fieldNames().forEachRemaining(s -> System.out.println("field:"+s));
        if (node.has(entityName)) {
            System.out.println("!!! found");
            return node.get(entityName);
        }
        if (!node.isContainerNode()) {
            return null;
        }
        for (JsonNode child : node) {
            System.out.println("Iterate:"+child);
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
//        String text = Util.fileToString("Reservation.json");
//
//        Reservation reservation = mapper.readValue(text, Reservation.class);
//        System.out.println("HotelCode: " + reservation.getHotelReservation().getHotel().getCode());
//        reservation.getHotelReservation().getGuests().forEach(guest -> System.out.println(guest.getPersonName()
//                .getGivenName() + ":" + guest.getPersonName().getSurname()));
//        Pattern pattern = Pattern.compile("(?=.*\\{.*)(?=hotelReservation)", Pattern.DOTALL);
//        if (pattern.matcher(text).find()) {
//            System.out.println("YESSSS!!");
//        }
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
