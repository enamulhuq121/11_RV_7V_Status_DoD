package com.icddrb.enamapppractice;



public class ParticipantModal {
    private int id;
    private String name;
    private String gender;
    private String status; // New variable
    private String date_of_birth; // Changed to String
    private String date_of_death; // New variable
    private String occupation;
    private String hobbies;


    public ParticipantModal(int id, String name, String gender, String status, String date_of_birth, String date_of_death, String occupation, String hobbies) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.date_of_birth = date_of_birth;
        this.date_of_death = date_of_death;
        this.occupation = occupation;
        this.hobbies = hobbies;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }


    public String getDateOfDeath() {
        return date_of_death;
    }

    public void setDateOfDeath(String date_of_death) {
        this.date_of_death = date_of_death;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }


}
