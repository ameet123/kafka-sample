import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.ameet.kafkasample.Util;
import org.ameet.kafkasample.model.ihg.EZMessage;
import org.ameet.kafkasample.model.ihg.json.Reservation;
import org.ameet.kafkasample.model.ihg.reservation.EnvelopeType;
import org.ameet.kafkasample.model.ihg.reservation.ObjectFactory;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

/**
 * Created by ameet.chaubal on 7/14/2017.
 */
public class TestEZProcessor {
    private static final String MODIFY_RESERV_REQ_XML_FILE = "ModifyreservationRequest.xml";
    private static ObjectMapper mapper = new ObjectMapper();

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
//        System.out.println(text);
        Reservation reservation = mapper.readValue(text, Reservation.class);
        System.out.println("HotelCode: "+reservation.getHotelReservation().getHotel().getCode());
        reservation.getHotelReservation().getGuests().forEach(guest -> System.out.println(guest.getPersonName()
                .getGivenName()+":"+guest.getPersonName().getSurname()));
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
