package Store;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ClientAction extends ProductAction implements SessionAware{
    private String email,firstName,lastName,userpassword;
    private Client client;
    private SessionMap<String, String> userSession ;


//    public String execute(){
//        if{
//            return "success";
//        }
//        else{
//            return "error";
//        }
//    }


    public void setSession(Map<String, Object> map) {
        userSession = (SessionMap) map ;

    }
//    public void setSession(Map map) {
//        sessionmap=(SessionMap)map;
//        sessionmap.put("login","true");
//    }

    public String logout(){
        userSession.invalidate();
        return "success";
    }

    public String registerNewClient(){
        try {
            client = new Client();
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setEmail(email);
            client.setUserpassword(userpassword);
            new DatabaseConnection().insertClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String clientRegistrationPage(){ return SUCCESS;
    }

    public String clientLoginPage(){
        return SUCCESS;
    }

    public String clientLogIn(){
        if(DatabaseConnection.clientLogIn(email,userpassword)) {
            userSession.put("login", "true");
            userSession.put("email", email);
        }
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        System.out.println(session.getAttribute("email"));
        String s=(String)session.getAttribute("login");
        if(s!=null && !s.equals("")){
            return "success";
        }
        else{
            return "error";
        }
    }


    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
