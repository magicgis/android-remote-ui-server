/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

public class Session {

    private String id;

    public Session(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Session[");
        sb.append("id:").append(id).append("]");

        return sb.toString();
    }
}
