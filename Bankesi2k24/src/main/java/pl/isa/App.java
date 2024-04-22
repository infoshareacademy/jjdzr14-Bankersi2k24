package pl.isa;

import pl.isa.dataAccess.Connector;
import pl.isa.dataAccess.ObjectToJson;
import pl.isa.model.PlainOldJavaObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "testing saving to file" );
        PlainOldJavaObject pojo1 = new PlainOldJavaObject();
        pojo1.setUserName("John Doe");
        pojo1.setAccountNumber(123456789);
        pojo1.setBornDate(new Date());

        PlainOldJavaObject pojo2 = new PlainOldJavaObject();
        pojo2.setUserName("Kevin Smith");
        pojo2.setAccountNumber(30000111);
        pojo2.setBornDate(new Date());

        List<PlainOldJavaObject> pojos = new ArrayList<>();
        pojos.add(pojo1);
        pojos.add(pojo2);


        Connector con = new Connector();
        ObjectToJson<PlainOldJavaObject> objectToJson = new ObjectToJson<> ();

        //this works
        //String json = objectToJson.convertObjectToJson(pojo1);

        String json = objectToJson.serialize(pojos);
        Boolean ret = con.save(json);

        //System.out.printf("%s\n%s%n", ret, json);


        System.out.println("tetsing reading from JSON");

        String jsonList = con.read();
        List<PlainOldJavaObject> pojoList = objectToJson.deserialize(jsonList, PlainOldJavaObject.class);
        for(PlainOldJavaObject obj : pojoList){
            System.out.printf("%s%n", obj);
        }
    }
}
