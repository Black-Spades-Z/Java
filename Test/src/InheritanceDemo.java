/*
ID : U2110207
NAME : Azizbek Muminjonov
Project : lab 3
*/

import java.lang.String;
import java.util.Scanner;


class Student
{

// Private members

    private String studentName;
    private int studentID;
    private double english;
    private double maths;

// Public members

    public Student(){}
    public Student(String sn, int sid, double eng, double m)
    {
        studentName = sn;
        studentID = sid;
        english = eng;
        maths = m;
    }
    public String getStudentName()
    {
        return studentName;
    }
    public void setStudentName(String name)
    {
        studentName = name;
    }
    public int getStudentID()
    {
        return studentID;
    }
    public void setStudentID(int ID)
    {
        studentID = ID;
    }
    public double getEnglish()
    {
        return english;
    }
    public void setEnglish(double eng)
    {
        english = eng;
    }
    public double getMaths()
    {
        return maths;
    }
    public void setMaths(double m)
    {
        maths = m;
    }
    public double total_Score()
    {
        return english + maths;
    }

}

class SOL_Student extends Student
{

// Private members

    private double computerProgramming;
    private double intro_to_Eco;

    // Public members
    SOL_Student(){};
    SOL_Student(String sn, int sid, double eng, double m, double cp, double ite)
    {
        super(sn,sid,eng,m);

        computerProgramming = cp;
        intro_to_Eco = ite;
    }
    public double getComputerProgramming()
    {
        return computerProgramming;
    }
    public void setComputerProgramming(double cp)
    {
        computerProgramming = cp;
    }
    public double getIntro_to_Eco()
    {
        return intro_to_Eco;
    }
    public void setIntro_to_Eco(double ite)
    {
        intro_to_Eco = ite;
    }
    public double total_Score()
    {
        return getEnglish() + getMaths() + intro_to_Eco + computerProgramming;
    }

}

class SOCIE_Student extends Student
{
    // Private members
    private double oop1;
    private double intro_to_IT;

// Public members

    SOCIE_Student(){}
    SOCIE_Student(String sn, int sid, double eng, double m, double oop, double iti)
    {
        super(sn,sid,eng,m);
        oop1 = oop;
        intro_to_IT = iti;
    }
    public double getOpp1()
    {
        return oop1;
    }
    public void setOop1(double oop)
    {
        oop1 = oop;
    }
    public double getIntro_to_IT()
    {
        return intro_to_IT;
    }
    public void setIntro_to_IT(double iti)
    {
        intro_to_IT = iti;
    }
    public double total_Score()
    {
        return getEnglish() + getMaths() + intro_to_IT + oop1;
    }
}






public class InheritanceDemo
{
    public static void displayObject (Object o)
    {
        if (o instanceof SOL_Student)
        {
            System.out.println("Name : "  + ((SOL_Student)o).getStudentName());
            System.out.println("ID : " + ((SOL_Student)o).getStudentID());
            System.out.println("English : " + ((SOL_Student)o).getEnglish());
            System.out.println("Maths : " + ((SOL_Student)o).getMaths());

            System.out.println("Computer Programming : " + ((SOL_Student)o).getComputerProgramming());
            System.out.println("Intro to ECO : " + ((SOL_Student)o).getIntro_to_Eco());
            System.out.println("Total score : " + ((SOL_Student)o).total_Score());
        }
        else if (o instanceof SOCIE_Student)
        {

            System.out.println("Name : "  + ((SOCIE_Student)o).getStudentName());
            System.out.println("ID : " + ((SOCIE_Student)o).getStudentID());
            System.out.println("English : " + ((SOCIE_Student)o).getEnglish());
            System.out.println("Maths : " + ((SOCIE_Student)o).getMaths());

            System.out.println("OOP1 : " + ((SOCIE_Student)o).getOpp1());
            System.out.println("Intro to IT : " + ((SOCIE_Student)o).getIntro_to_IT());
            System.out.println("Total score : " + ((SOCIE_Student)o).total_Score());
        }
    }

    public static void main(String[] args)
    {

        System.out.println("SOL");
        displayObject(new SOL_Student());
        System.out.println("SOCIE");
        displayObject(new SOCIE_Student());
    }
}

