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
    private String email, firstName, lastName, userPassword;

    private Client client;
    private SessionMap userSession;

    public void setSession(Map<String, Object> map) {
        userSession = (SessionMap) map;

    }

    public String logout() {
        userSession.invalidate();
        return "logOut";
    }

    public String registerNewClient() {
        try {
            ClientDao.insertClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "registerNewClient";
    }

    public String clientRegistrationPage() {
        return "clientRegistrationPage";
    }

    public String clientLoginPage() {
        return "clientLoginPage";
    }

    public String clientLogIn() {
        if (ClientDao.clientLogIn(client.getEmail(), client.getUserPassword())) {
            userSession.put("email", client.getEmail());
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String s = (String) session.getAttribute("email");
        if (s != null && !s.equals("")) {
            return "success";
        } else {
            return "error";
        }
    }

    public String profilePage() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String s = (String) session.getAttribute("email");
        client=ClientDao.fetchClientProfile();
        if (s != null && !s.equals("")) {
            return "success";
        } else {
            return "error";
        }
    }

    public String updateClientInfo(){
        try {
            ClientDao.updateClientInfo(client);
            userSession.put("email",client.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "updateClientInfo";
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
