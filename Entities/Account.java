package Entities;

import java.lang.*;
import java.util.*;
import java.io.*;
import Frames.*;

public class Account
{
    private String name;
    private String uname;
    private String upass;
    private String phone;
    private String blood;
    private File myfile;
    private FileWriter fwriter;
    private Scanner sc;

    public Account()
    {

    }

    public Account(String name,String uname,String upass,String phone,String blood)
    {
        this.name=name;
        this.uname=uname;
        this.upass=upass;
        this.phone=phone;
        this.blood=blood;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setUname(String uname)
    {
        this.uname=uname;
    }

    public void setUpass(String upass)
    {
        this.upass=upass;
    }

    public void setPhone(String phone)
    {
        this.phone=phone;
    }

    public void setBlood(String blood)
    {
        this.blood=blood;
    }

    public String getName()
    {
        return name;
    }

    public String getUname()
    {
        return uname;
    }

    public String getUpass()
    {
        return upass;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getBlood()
    {
        return blood;
    }

    public void addAccount()
    {
        try
        {
            myfile=new File("./Datas/Data.txt");
            myfile.createNewFile();
            fwriter=new FileWriter(myfile,true);

            fwriter.write(getName()+"\t");
            fwriter.write(getUname()+"\t");
            fwriter.write(getUpass()+"\t");
            fwriter.write(getPhone()+"\t");
            fwriter.write(getBlood()+"\n");

            fwriter.flush();
            fwriter.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public boolean getAccount(String s1,String s2)
    {
        boolean flag=false;

        try
        {
            myfile=new File("./Datas/Data.txt");
            myfile.createNewFile();
            sc=new Scanner(myfile);

            while(sc.hasNextLine())
            {
                String line=sc.nextLine();
                if(line.trim().isEmpty())
                {
                    continue;
                }

                String value[]=line.split("\\t");

                if(value.length>=5 && value[1].equals(s1)&&value[2].equals(s2))
                {
                    flag=true;
                }
            }

            sc.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        return flag;
    }

    public static List<String[]> loadAllAccounts()
    {
        List<String[]> list=new ArrayList<String[]>();

        try
        {
            File file=new File("./Datas/Data.txt");
            file.getParentFile().mkdirs();
            file.createNewFile();

            Scanner sc=new Scanner(file);

            while(sc.hasNextLine())
            {
                String line=sc.nextLine();

                if(line.trim().isEmpty())
                {
                    continue;
                }

                String value[]=line.split("\\t");

                if(value.length==5)
                {
                    list.add(value);
                }
            }

            sc.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        return list;
    }

    public static void saveAllAccounts(List<String[]> list)
    {
        try
        {
            File file=new File("./Datas/Data.txt");
            file.getParentFile().mkdirs();

            FileWriter fw=new FileWriter(file,false);

            for(String[] row:list)
            {
                fw.write(row[0]+"\t");
                fw.write(row[1]+"\t");
                fw.write(row[2]+"\t");
                fw.write(row[3]+"\t");
                fw.write(row[4]+"\n");
            }

            fw.flush();
            fw.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static boolean usernameExists(String username)
    {
        for(String[] row:loadAllAccounts())
        {
            if(row.length>=2 && row[1].equals(username))
            {
                return true;
            }
        }

        return false;
    }
}
