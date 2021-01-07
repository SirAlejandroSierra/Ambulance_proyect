/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patient;

import BITalino.Frame;
import Patient.Gender;
import Patient.Level;
import Patient.Family;
import Patient.LevelUnknown;
import Patient.Event;
import Patient.BasicOptions;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author AdriCortellucci
 */
public class Patient implements Serializable {

    private static final long serialVersionUID = -6291904286218553733L;

    private Ambulance ambulance;
    private Date date;
    private String name;
    private String id;
    private boolean accurateAge;
    private int age;
    private Gender gender;
    private boolean overweight;

    private Float systolicPressure;
    private Float diastolicPressure;
    private LevelUnknown tension;
    private Level heartRate;
    private BasicOptions smoker;
    private BasicOptions drinker;
    private BasicOptions diabetic;
    private LevelUnknown chosterol;
    private Event previousEvent;
    private Family familyHistory;

    private BasicOptions chestPressure;
    private BasicOptions chestPain;
    private BasicOptions NeckPain;
    private BasicOptions armPain;
    private BasicOptions backPain;
    private boolean shortnessOfBreath;
    private boolean sweating;
    private BasicOptions nausea;
    private BasicOptions vomiting;
    private BasicOptions anxiety;
    private BasicOptions cough;
    private BasicOptions dizziness;
    private String notes;
    
    private ArrayList<Integer> recordedECG;

    public Patient() {
        this.date = null;
        this.name = "";
        this.id = "";
        this.accurateAge = false;
        this.age = 0;
        this.gender = null;
        this.overweight = false;
        this.systolicPressure = null;
        this.diastolicPressure = null;
        this.tension = null;
        this.heartRate = null;
        this.smoker = null;
        this.drinker = null;
        this.diabetic = null;
        this.chosterol = null;
        this.previousEvent = null;
        this.familyHistory = null;
        this.chestPressure = null;
        this.chestPain = null;
        this.NeckPain = null;
        this.armPain = null;
        this.backPain = null;
        this.shortnessOfBreath = false;
        this.sweating = false;
        this.nausea = null;
        this.vomiting = null;
        this.anxiety = null;
        this.cough = null;
        this.dizziness = null;
        this.notes = "";
        this.recordedECG = new ArrayList();
    }

    
    public Patient(String name, String id, boolean accurateAge, int age, Gender gender, boolean overweight, Float systolicPressure, Float diastolicPressure, LevelUnknown tension, Level heartRate, BasicOptions smoker, BasicOptions drinker, BasicOptions diabetic, LevelUnknown chosterol, Event previousEvent, Family familyHistory, BasicOptions chestPressure, BasicOptions chestPain, BasicOptions NeckPain, BasicOptions armPain, BasicOptions backPain, boolean shortnessOfBreath, boolean sweating, BasicOptions nausea, BasicOptions vomiting, BasicOptions anxiety, BasicOptions cough, BasicOptions dizziness, String notes, ArrayList<Integer> recordedECG) {
        this.name = name;
        this.id = id;
        this.accurateAge = accurateAge;
        this.age = age;
        this.gender = gender;
        this.overweight = overweight;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.tension = tension;
        this.heartRate = heartRate;
        this.smoker = smoker;
        this.drinker = drinker;
        this.diabetic = diabetic;
        this.chosterol = chosterol;
        this.previousEvent = previousEvent;
        this.familyHistory = familyHistory;
        this.chestPressure = chestPressure;
        this.chestPain = chestPain;
        this.NeckPain = NeckPain;
        this.armPain = armPain;
        this.backPain = backPain;
        this.shortnessOfBreath = shortnessOfBreath;
        this.sweating = sweating;
        this.nausea = nausea;
        this.vomiting = vomiting;
        this.anxiety = anxiety;
        this.cough = cough;
        this.dizziness = dizziness;
        this.notes = notes;
        this.recordedECG = recordedECG;
    }

    public void setBasicInformation(String name, String id, boolean approxAge, int age, Gender gender, boolean overweight) {
        this.name = name;
        this.id = id;
        this.accurateAge = approxAge;
        this.age = age;
        this.gender = gender;
        this.overweight = overweight;
    }

    public void setMedicalInformation(Float systolicPressure, Float diastolicPressure, LevelUnknown tension, Level heartRate, BasicOptions smoker, BasicOptions drinker, BasicOptions diabetic, LevelUnknown chosterol, Event previousEvent, Family familyHistory) {

        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.tension = tension;
        this.heartRate = heartRate;
        this.smoker = smoker;
        this.drinker = drinker;
        this.diabetic = diabetic;
        this.chosterol = chosterol;
        this.previousEvent = previousEvent;
        this.familyHistory = familyHistory;

    }

    public void setMedicalInformation(BasicOptions chestPressure, BasicOptions chestPain, BasicOptions NeckPain, BasicOptions armPain, BasicOptions backPain, boolean shortnessOfBreath, boolean sweating, BasicOptions nausea, BasicOptions vomiting, BasicOptions anxiety, BasicOptions cough, BasicOptions dizziness, String notes) {

        this.chestPressure = chestPressure;
        this.chestPain = chestPain;
        this.NeckPain = NeckPain;
        this.armPain = armPain;
        this.smoker = smoker;
        this.backPain = backPain;
        this.shortnessOfBreath = shortnessOfBreath;
        this.sweating = sweating;
        this.nausea = nausea;
        this.vomiting = vomiting;
        this.anxiety = anxiety;
        this.cough = cough;
        this.dizziness = dizziness;
        this.notes = notes;

    }
    
    public void setECG (ArrayList<Integer> ecgValues){
        this.recordedECG = ecgValues;
    }
    public void setECGToZero(){
        this.recordedECG.clear();
    }
    public ArrayList getRecordedECG(){
        return recordedECG;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Ambulance getAmbulance() {
        return ambulance;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isAccurateAge() {
        return accurateAge;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isOverweight() {
        return overweight;
    }

    public Float getSystolicPressure() {
        return systolicPressure;
    }

    public Float getDiastolicPressure() {
        return diastolicPressure;
    }

    public LevelUnknown getTension() {
        return tension;
    }

    public Level getHeartRate() {
        return heartRate;
    }

    public BasicOptions getSmoker() {
        return smoker;
    }

    public BasicOptions getDrinker() {
        return drinker;
    }

    public BasicOptions getDiabetic() {
        return diabetic;
    }

    public LevelUnknown getChosterol() {
        return chosterol;
    }

    public Event getPreviousEvent() {
        return previousEvent;
    }

    public Family getFamilyHistory() {
        return familyHistory;
    }

    public BasicOptions getChestPressure() {
        return chestPressure;
    }

    public BasicOptions getChestPain() {
        return chestPain;
    }

    public BasicOptions getNeckPain() {
        return NeckPain;
    }

    public BasicOptions getArmPain() {
        return armPain;
    }

    public BasicOptions getBackPain() {
        return backPain;
    }

    public boolean getShortnessOfBreath() {
        return shortnessOfBreath;
    }

    public boolean getSweating() {
        return sweating;
    }

    public BasicOptions getNausea() {
        return nausea;
    }

    public BasicOptions getVomiting() {
        return vomiting;
    }

    public BasicOptions getAnxiety() {
        return anxiety;
    }

    public BasicOptions getCough() {
        return cough;
    }

    public BasicOptions getDizziness() {
        return dizziness;
    }

    public String getNotes() {
        return notes;
    }
    

    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccurateAge(boolean accurateAge) {
        this.accurateAge = accurateAge;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setOverweight(boolean overweight) {
        this.overweight = overweight;
    }

    public void setSystolicPressure(Float systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public void setDiastolicPressure(Float diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public void setTension(LevelUnknown tension) {
        this.tension = tension;
    }

    public void setHeartRate(Level heartRate) {
        this.heartRate = heartRate;
    }

    public void setSmoker(BasicOptions smoker) {
        this.smoker = smoker;
    }

    public void setDrinker(BasicOptions drinker) {
        this.drinker = drinker;
    }

    public void setDiabetic(BasicOptions diabetic) {
        this.diabetic = diabetic;
    }

    public void setChosterol(LevelUnknown chosterol) {
        this.chosterol = chosterol;
    }

    public void setPreviousEvent(Event previousEvent) {
        this.previousEvent = previousEvent;
    }

    public void setFamilyHistory(Family familyHistory) {
        this.familyHistory = familyHistory;
    }

    public void setChestPressure(BasicOptions chestPressure) {
        this.chestPressure = chestPressure;
    }

    public void setChestPain(BasicOptions chestPain) {
        this.chestPain = chestPain;
    }

    public void setNeckPain(BasicOptions NeckPain) {
        this.NeckPain = NeckPain;
    }

    public void setArmPain(BasicOptions armPain) {
        this.armPain = armPain;
    }

    public void setBackPain(BasicOptions backPain) {
        this.backPain = backPain;
    }

    public void setShortnessOfBreath(boolean shortnessOfBreath) {
        this.shortnessOfBreath = shortnessOfBreath;
    }

    public void setSweating(boolean sweating) {
        this.sweating = sweating;
    }

    public void setNausea(BasicOptions nausea) {
        this.nausea = nausea;
    }

    public void setVomiting(BasicOptions vomiting) {
        this.vomiting = vomiting;
    }

    public void setAnxiety(BasicOptions anxiety) {
        this.anxiety = anxiety;
    }

    public void setCough(BasicOptions cough) {
        this.cough = cough;
    }

    public void setDizziness(BasicOptions dizziness) {
        this.dizziness = dizziness;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    


    public String print() {
        return ambulance + "\n"
                + name + "\n"
                + id + "\n"
                + accurateAge + "\n"
                + age + "\n"
                + gender + "\n"
                + overweight + "\n"
                + systolicPressure + "\n"
                + diastolicPressure + "\n"
                + tension + "\n"
                + heartRate + "\n"
                + smoker + "\n"
                + drinker + "\n"
                + diabetic + "\n"
                + chosterol + "\n"
                + previousEvent + "\n"
                + familyHistory + "\n"
                + chestPressure + "\n"
                + chestPain + "\n"
                + NeckPain + "\n"
                + armPain + "\n"
                + backPain + "\n"
                + shortnessOfBreath + "\n"
                + sweating + "\n"
                + nausea + "\n"
                + vomiting + "\n"
                + anxiety + "\n"
                + cough + "\n"
                + dizziness + "\n"
                + notes;
    }

    @Override
    public String toString() {
        return "Patient{" + "ambulance=" + ambulance + ", name=" + name + ", id=" + id + ", accurateAge=" + accurateAge + ", age=" + age + ", gender=" + gender + ", overweight=" + overweight + ", systolicPressure=" + systolicPressure + ", diastolicPressure=" + diastolicPressure + ", tension=" + tension + ", heartRate=" + heartRate + ", smoker=" + smoker + ", drinker=" + drinker + ", diabetic=" + diabetic + ", chosterol=" + chosterol + ", previousEvent=" + previousEvent + ", familyHistory=" + familyHistory + ", chestPressure=" + chestPressure + ", chestPain=" + chestPain + ", NeckPain=" + NeckPain + ", armPain=" + armPain + ", backPain=" + backPain + ", shortnessOfBreath=" + shortnessOfBreath + ", sweating=" + sweating + ", nausea=" + nausea + ", vomiting=" + vomiting + ", anxiety=" + anxiety + ", cough=" + cough + ", dizziness=" + dizziness + ", notes=" + notes + '}';
    }

}
