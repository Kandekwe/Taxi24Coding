
package rw.bk.taxi24app.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 *@return Payload of the response.
 *@return Response code
 *@return Description of the response. It is optional.
 * **/



@XmlRootElement
@JsonPropertyOrder(value = {"responseCode", "responseDescription", "entity"})
@XmlType(propOrder = {"responseCode", "responseDescription", "entity"})
public class ApiResponse<T> {

    protected String responseCode;
    protected String responseDescription;
    private T entity;

    public ApiResponse() {
    }

    public ApiResponse(String responseCode, String responseDescription, T entity) {
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.entity = entity;
    }

    ApiResponse(String responseCode, String responseDescription) {
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
    }


    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }


    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }


    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

}
