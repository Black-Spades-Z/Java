/*
Name : Azizbek Muminjonov
ID : U2110207
 */

// Step 1: Importing SQL and Scanner libraries

import java.sql.*;
import java.util.Scanner;



public class BMI_DB {

    // Writing print function to easier printing things :)

    public static void print(String something)
    {
        System.out.println(something);
    }
    public static void print(String something, boolean sameLine)
    {
        System.out.print(something);
    }


    // Function to select and display data
    public static void selection(Statement stmt)
    {
        String strSelect, name;
        int index, number;
        boolean state = true;
        Scanner read = new Scanner(System.in);


        try
        {
            while(state) {


                print("\n\n\t\t\tMenu\n");
                print("1.Display all");
                print("2.Select by ID");
                print("3.Select by Name");
                print("4.Select by weight");
                print("5.Select by height");
                print("0.Exit");

                print("Index : ", true);

                index = read.nextInt();

                switch (index) {
                    case 0: {
                        state = false;
                        break;
                    }
                    case 1: {
                        strSelect = "select * from BMI";
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();

                        ResultSet rset = null;
                        rset = stmt.executeQuery(strSelect);


                        //  Process the ResultSet by scrolling the cursor forward via next().
                        //  For each row, retrieve the contents of the cells with getXxx(columnName).


                        System.out.println("The records selected are:");
                        int rowCount = 0;
                        while (rset.next()) {    // Move the cursor to the next row, return false if no more row
                            String pID = rset.getString("PersonID");
                            String pName = rset.getString("PersonName");
                            String pWeight = rset.getString("Weight");
                            String pHeight = rset.getString("Height");

                            System.out.println(pID + " " + pName + " " + pWeight + " " + pHeight);
                            ++rowCount;
                        }
                        System.out.println("Total : " + rowCount);
                        break;
                    }
                    case 2: {
                        print("ID : ", true);
                        number = read.nextInt();
                        strSelect = "select * from BMI where PersonID = " + number;
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();

                        ResultSet rset = null;
                        rset = stmt.executeQuery(strSelect);

                        if (!rset.wasNull())
                        {
                            print("Not found");
                            break;
                        }

                        //  Process the ResultSet by scrolling the cursor forward via next().
                        //  For each row, retrieve the contents of the cells with getXxx(columnName).

                        System.out.println("The records selected are:");
                        int rowCount = 0;
                        while (rset.next()) {    // Move the cursor to the next row, return false if no more row
                            String pID = rset.getString("PersonID");
                            String pName = rset.getString("PersonName");
                            String pWeight = rset.getString("Weight");
                            String pHeight = rset.getString("Height");

                            System.out.println(pID + " " + pName + " " + pWeight + " " + pHeight);
                            ++rowCount;
                        }
                        System.out.println("Total : " + rowCount);
                        break;
                    }
                    case 3: {
                        print("Name : ", true);
                        name = read.next();
                        strSelect = "select * from BMI where PersonName = " + "\"" + name + "\"";
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();

                        ResultSet rset = null;
                        rset = stmt.executeQuery(strSelect);

                        if (!rset.wasNull())
                        {
                            print("Not found");
                            break;
                        }

                        //  Process the ResultSet by scrolling the cursor forward via next().
                        //  For each row, retrieve the contents of the cells with getXxx(columnName).

                        System.out.println("The records selected are:");
                        int rowCount = 0;
                        while (rset.next()) {    // Move the cursor to the next row, return false if no more row
                            String pID = rset.getString("PersonID");
                            String pName = rset.getString("PersonName");
                            String pWeight = rset.getString("Weight");
                            String pHeight = rset.getString("Height");

                            System.out.println(pID + " " + pName + " " + pWeight + " " + pHeight);
                            ++rowCount;
                        }
                        System.out.println("Total : " + rowCount);
                        break;
                    }
                    case 4: {
                        print("Weight : ", true);
                        number = read.nextInt();
                        strSelect = "select * from BMI where Weight = " + number;
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();

                        ResultSet rset = null;
                        rset = stmt.executeQuery(strSelect);

                        if (!rset.wasNull())
                        {
                            print("Not found");
                            break;
                        }

                        //  Process the ResultSet by scrolling the cursor forward via next().
                        //  For each row, retrieve the contents of the cells with getXxx(columnName).

                        System.out.println("The records selected are:");
                        int rowCount = 0;
                        while (rset.next()) {    // Move the cursor to the next row, return false if no more row
                            String pID = rset.getString("PersonID");
                            String pName = rset.getString("PersonName");
                            String pWeight = rset.getString("Weight");
                            String pHeight = rset.getString("Height");

                            System.out.println(pID + " " + pName + " " + pWeight + " " + pHeight);
                            ++rowCount;
                        }
                        System.out.println("Total : " + rowCount);
                        break;
                    }
                    case 5:
                    {
                        print("Height : ", true);
                        number = read.nextInt();
                        strSelect = "select * from BMI where Height = " + number;
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();

                        ResultSet rset = null;
                        rset = stmt.executeQuery(strSelect);

                        if (!rset.wasNull())
                        {
                            print("Not found");
                            break;
                        }

                        //  Process the ResultSet by scrolling the cursor forward via next().
                        //  For each row, retrieve the contents of the cells with getXxx(columnName).

                        System.out.println("The records selected are:");
                        int rowCount = 0;
                        while (rset.next()) {    // Move the cursor to the next row, return false if no more row
                            String pID = rset.getString("PersonID");
                            String pName = rset.getString("PersonName");
                            String pWeight = rset.getString("Weight");
                            String pHeight = rset.getString("Height");

                            System.out.println(pID + " " + pName + " " + pWeight + " " + pHeight);
                            ++rowCount;
                        }
                        System.out.println("Total : " + rowCount);
                        break;
                    }
                    default: {
                        print("Wrong input try again T_T");
                        break;
                    }
                }

            }
        }catch (SQLException e)
        {
            print("Unexpected error occurred. Try again T_T\n\n");
        }
    }


    // Function to insert data
    public static void insertion(Statement stmt)
    {
        String strSelect, name;
        int index, id, weight,height;
        Scanner read = new Scanner(System.in);

        try {
            print("Enter ID : ", true);
            id = read.nextInt();

            print("Enter name : ", true);
            name = read.next();

            print("Enter weight : ", true);
            weight = read.nextInt();

            print("Enter height : ", true);
            height = read.nextInt();

            // We use execute Update as we need to insert new data to database

            strSelect = "insert into bmi values ( " +id+","+"\"" +name+"\"" +","+ weight+ "," + height + " );";
            System.out.println("The SQL query is: " + strSelect); // Echo For debugging
            System.out.println();

            stmt.executeUpdate(strSelect);

        }catch (SQLException e)
        {
            print("Unexpected error occurred. Try again T_T\n\n");
        }
    }

    // Function to delete data
    public static void deletion(Statement stmt)
    {
        String strSelect, name;
        int index, number;
        boolean state = true;
        Scanner read = new Scanner(System.in);


        try
        {
            while(state) {


                print("\n\n\t\t\tMenu\n");
                print("1.Delete all");
                print("2.Delete by ID");
                print("3.Delete by Name");
                print("4.Delete by weight");
                print("5.Delete by height");
                print("0.Exit");

                print("Index : ", true);

                index = read.nextInt();

                switch (index) {
                    case 0: {
                        state = false;
                        break;
                    }
                    case 1: {
                        strSelect = "delete * from BMI;";
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();

                        int count = stmt.executeUpdate(strSelect);
                        print("Deleted : " + count);
                        break;
                    }
                    case 2: {
                        print("ID : ", true);
                        number = read.nextInt();
                        strSelect = "delete from BMI where PersonID = " + number + ";";
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();

                        int count = stmt.executeUpdate(strSelect);
                        print("Deleted : " + count);
                        break;
                    }
                    case 3: {
                        print("Name : ", true);
                        name = read.next();
                        strSelect = "delete from BMI where PersonName = " + "\"" + name + "\";";
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();

                        int count = stmt.executeUpdate(strSelect);
                        print("Deleted : " + count);
                        break;
                    }
                    case 4: {
                        print("Weight : ", true);
                        number = read.nextInt();
                        strSelect = "delete from BMI where Weight = " + number+ ";";
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();
                        int count = stmt.executeUpdate(strSelect);
                        print("Deleted : " + count);
                        break;
                    }
                    case 5:
                    {
                        print("Height : ", true);
                        number = read.nextInt();
                        strSelect = "delete from BMI where Height = " + number + ";";
                        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                        System.out.println();

                        int count = stmt.executeUpdate(strSelect);
                        print("Deleted : " + count);
                        break;
                    }
                    default: {
                        print("Wrong input try again T_T");
                        break;
                    }
                }

            }
        }catch (SQLException e)
        {
            print("Unexpected error occurred. Try again T_T\n\n");
        }
    }

    // Function to update data
    public static void update(Connection conn)
    {
        String param, name;
        int index, number, id, weight, height;
        boolean state = true;
        Scanner read = new Scanner(System.in);

        try {

            while(state) {


                print("\n\n\t\t\tMenu\n");
                print("1.Update by ID");
                print("2.Update by Name");
                print("3.Update by weight");
                print("4.Update by height");
                print("0.Exit");

                print("Index : ", true);

                index = read.nextInt();

                switch (index) {
                    case 0: {
                        state = false;
                        break;
                    }
                    case 1: {
                        print("Enter ID where update : ", true);
                        number = read.nextInt();

                        print("Enter ID to be updated : ", true);
                        id = read.nextInt();

                        print("Enter Name to be updated : ", true);
                        name = read.next();

                        print("Enter Weight to be updated : ", true);
                        weight = read.nextInt();

                        print("Enter Height to be updated : ", true);
                        height = read.nextInt();


                        PreparedStatement stmt = conn.prepareStatement("update bmi set PersonID=?, PersonName=?, Weight=?, Height=? where PersonID =?");

                        stmt.setInt(1,id);
                        stmt.setString(2,("\"" + name + "\""));
                        stmt.setInt(3,weight);
                        stmt.setInt(4,height);
                        stmt.setInt(5, number);

                        int i=stmt.executeUpdate();
                        print("Updated : " + i);
                        break;
                    }
                    case 2: {
                        print("Enter Name where update : ", true);
                        param = read.next();

                        print("Enter ID to be updated : ", true);
                        id = read.nextInt();

                        print("Enter Name to be updated : ", true);
                        name = read.next();

                        print("Enter Weight to be updated : ", true);
                        weight = read.nextInt();

                        print("Enter Height to be updated : ", true);
                        height = read.nextInt();


                        PreparedStatement stmt = conn.prepareStatement("update bmi set PersonID=?, PersonName=?, Weight=?, Height=? where PersonName =?");

                        stmt.setInt(1,id);
                        stmt.setString(2,("\"" + name + "\""));
                        stmt.setInt(3,weight);
                        stmt.setInt(4,height);
                        stmt.setString(5, param);

                        int i=stmt.executeUpdate();
                        print("Updated : " + i);
                        break;
                    }
                    case 3: {
                        print("Enter Weight where update : ", true);
                        number = read.nextInt();

                        print("Enter ID to be updated : ", true);
                        id = read.nextInt();

                        print("Enter Name to be updated : ", true);
                        name = read.next();

                        print("Enter Weight to be updated : ", true);
                        weight = read.nextInt();

                        print("Enter Height to be updated : ", true);
                        height = read.nextInt();


                        PreparedStatement stmt = conn.prepareStatement("update bmi set PersonID=?, PersonName=?, Weight=?, Height=? where Weight =?");

                        stmt.setInt(1,id);
                        stmt.setString(2,("\"" + name + "\""));
                        stmt.setInt(3,weight);
                        stmt.setInt(4,height);
                        stmt.setInt(5, number);

                        int i=stmt.executeUpdate();
                        print("Updated : " + i);
                        break;
                    }
                    case 4: {
                        print("Enter Height where update : ", true);
                        number = read.nextInt();

                        print("Enter ID to be updated : ", true);
                        id = read.nextInt();

                        print("Enter Name to be updated : ", true);
                        name = read.next();

                        print("Enter Weight to be updated : ", true);
                        weight = read.nextInt();

                        print("Enter Height to be updated : ", true);
                        height = read.nextInt();


                        PreparedStatement stmt = conn.prepareStatement("update bmi set PersonID=?, PersonName=?, Weight=?, Height=? where Height =?");

                        stmt.setInt(1,id);
                        stmt.setString(2,("\"" + name + "\""));
                        stmt.setInt(3,weight);
                        stmt.setInt(4,height);
                        stmt.setInt(5, number);

                        int i=stmt.executeUpdate();
                        print("Updated : " + i);
                        break;
                    }
                    default: {
                        print("Wrong input try again T_T");
                        break;
                    }
                }

            }

            PreparedStatement stmt = conn.prepareStatement("update bmi set PersonID=?, PersonName=?, Weight=?, Height=? where PersonName =?");

            stmt.setInt(1,101);
            stmt.setString(2,"Sonoo");//1 specifies the first parameter in the query i.e. name
            stmt.setInt(3,101);
            stmt.setInt(4,101);
            stmt.setString(5, "Null");

            int i=stmt.executeUpdate();
            System.out.println(i+" records updated");
        }catch (SQLException e)
        {
            print("Unexpected error occurred. Try again T_T\n\n");
        }
    }


    public static void main(String[] args) throws SQLException {

        // Step 2: Declaring variables

        String password = "null";
        int index;
        boolean state = true;

        Scanner read = new Scanner(System.in);
        while(state) {

            print("\t\t\t\tWelcome to SQL");
            print("\n~~Always enter \"exit\" to quit~~\n");
            print("Enter your password : ", true);


            password = read.next();

            if (password.equals("exit"))
            {
                state = false;
                break;
            }

            try {
                // Step 3: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BMI_DB?autoReconnect=true&useSSL=false", "root", password);
                // Step 4: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();

                while (state) {
                    print("\n\n\t\t\tMenu\n");
                    print("1.Select");
                    print("2.Insert");
                    print("3.Delete");
                    print("4.Update");
                    print("0.Exit");

                    print("Index : ", true);

                    index = read.nextInt();

                    switch (index) {
                        case 0: {
                            state = false;
                            break;
                        }
                        case 1: {
                            selection(stmt);
                            break;
                        }
                        case 2: {
                            insertion(stmt);
                            break;
                        }
                        case 3: {
                            deletion(stmt);
                            break;
                        }
                        case 4: {
                            update(conn);
                            break;
                        }
                        default: {
                            print("Wrong input try again T_T");
                            break;
                        }
                    }
                }
                state = false;
                conn.close();

            // catching errors, expected and unexpected

            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException er)
            {
                print("Couldn't find database or wrong password");
            } catch (Exception e) {
                print(String.valueOf(e));
                System.out.println("Unexpected error occurred T_T \nTry again\n\n\n\n");
            }
        }
    }
}
