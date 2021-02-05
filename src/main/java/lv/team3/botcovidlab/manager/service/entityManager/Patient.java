package lv.team3.botcovidlab.manager.service.entityManager;

public class Patient {
     Long chatId;
     String name;
     String lastName;
     String personalCode;
     String temperature;
     boolean contactPerson;
     boolean hasCough;
     boolean hasTroubleBreathing;
     boolean hasHeadache;
     String phoneNumber;

    public Patient() {

    }

    public Long getChatId() {
        return chatId;
    }
    public void setChatId(Long id) {
        this.chatId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public boolean isContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(boolean contactPerson) {
        this.contactPerson = contactPerson;
    }

    public boolean isHasCough() {
        return hasCough;
    }

    public void setHasCough(boolean hasCough) {
        this.hasCough = hasCough;
    }

    public boolean isHasTroubleBreathing() {
        return hasTroubleBreathing;
    }

    public void setHasTroubleBreathing(boolean hasTroubleBreathing) {
        this.hasTroubleBreathing = hasTroubleBreathing;
    }

    public boolean isHasHeadache() {
        return hasHeadache;
    }

    public void setHasHeadache(boolean hasHeadache) {
        this.hasHeadache = hasHeadache;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "chatId=" + chatId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalCode='" + personalCode + '\'' +
                ", temperature=" + temperature +
                ", isContactPerson=" + contactPerson +
                ", hasCough=" + hasCough +
                ", hasTroubleBreathing=" + hasTroubleBreathing +
                ", hasHeadache=" + hasHeadache +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
