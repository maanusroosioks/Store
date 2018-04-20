package Store.Action;

import Store.Dao.ClientDao;
import Store.Model.Client;
import Store.Dao.ProductDao;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ClientAction extends ProductAction implements SessionAware {
    private String email, firstName, lastName, userpassword;

    private Client client;
    private SessionMap userSession;

    public void setSession(Map<String, Object> map) {
        userSession = (SessionMap) map;

    }
    public String logout() {
        userSession.invalidate();
        return "success";
    }

    public String registerNewClient() {
        try {
            ClientDao.insertClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String clientRegistrationPage() {
        return SUCCESS;
    }

    public String clientLoginPage() {
        return SUCCESS;
    }

    public String clientLogIn() {
        if (ClientDao.clientLogIn(client.getEmail(), client.getUserpassword())) {
            userSession.put("login", "true");
            userSession.put("email", client.getEmail());
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String s = (String) session.getAttribute("login");
        if (s != null && !s.equals("")) {
            return "success";
        } else {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
