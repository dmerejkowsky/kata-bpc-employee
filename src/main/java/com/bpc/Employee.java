package com.bpc;

public class Employee {

    private String nom;
    private String prenom;
    private String adresse;
    private String grade;
    private String responsable;
    private String moisNaiss;
    private String moisEmb;

    private int insee;
    private int jNaiss;
    private int jEmb;
    private int aNaiss;
    private int aEmb;


    public Employee() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getMoisNaiss() {
        return moisNaiss;
    }

    public void setMoisNaiss(String moisNaiss) {
        this.moisNaiss = moisNaiss;
    }

    public String getMoisEmb() {
        return moisEmb;
    }

    public void setMoisEmb(String moisEmb) {
        this.moisEmb = moisEmb;
    }

    public int getInsee() {
        return insee;
    }

    public void setInsee(int insee) {
        this.insee = insee;
    }

    public int getjNaiss() {
        return jNaiss;
    }

    public void setjNaiss(int jNaiss) {
        this.jNaiss = jNaiss;
    }

    public int getjEmb() {
        return jEmb;
    }

    public void setjEmb(int jEmb) {
        this.jEmb = jEmb;
    }

    public int getaNaiss() {
        return aNaiss;
    }

    public void setaNaiss(int aNaiss) {
        this.aNaiss = aNaiss;
    }

    public int getaEmb() {
        return aEmb;
    }

    public void setaEmb(int aEmb) {
        this.aEmb = aEmb;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass())
            return false;
        Employee emp = (Employee)o;
        if (!emp.nom.equals(nom))
            return false;
        if (!emp.prenom.equals(prenom))
            return false;
        return emp.insee == insee;
    }
}
