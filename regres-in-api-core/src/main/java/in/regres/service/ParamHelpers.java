package in.regres.service;

import in.regres.model.User;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ParamHelpers {

    public static List<NameValuePair> userToCreateParams(final User user) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("name", user.getName()));
        nameValuePairs.add(new BasicNameValuePair("job", user.getJob()));
        return nameValuePairs;
    }

    public static List<NameValuePair> userToLoginData(final User user) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("email", user.getEmail()));
        nameValuePairs.add(new BasicNameValuePair("password", user.getPassword()));
        return nameValuePairs;
    }
}
