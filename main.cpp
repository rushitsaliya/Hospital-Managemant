#include<iostream>
#include<conio.h>
#include<stdlib.h>
#include<string>
#include<fstream>

using namespace std;

int ContinuePermission()
{
    int response=0;

    cout<<endl<<"Press 1. to continue"<<endl;
    cin>>response;

    if(response==1)
        return 1;
    else
        return 0;
}

typedef struct
{
    int Pid;
    int age;
    string name;
    string city;
    string prescription;
    string contact_no;
    int Eid;
}PATIENT;

typedef struct
{
    int Eid;
    int age;
    string name;
    string city;
    string contact_no;
}EMPLOYEE;


class Patient;

//Parent Class
class Employee
{
protected:
    EMPLOYEE e;

public:

    //Parameterized constructor
    Employee(int Eid, int age, string name, string contact_no, string city="")
    {
        e.Eid=Eid;
        e.age=age;
        e.name=name;
        e.city=city;
        e.contact_no=contact_no;
    }

    //Parameterless constructor
    Employee()
    {
        e.Eid=NULL;
        e.age=NULL;
        e.name="";
        e.city="";
        e.contact_no="";
    }

    void getData()
    {
        cout<<cout<<"ID: "<<e.Eid<<endl<<"Name: "<<e.name<<endl<<"Age: "<<e.age<<endl<<"city: "<<e.city<<endl;
        cout<<"Contact No.: "<<e.contact_no<<endl;
    }

};

//Child Class of class Employee
class Doctor: public Employee
{
public:
    Doctor(int Eid, int age, string name, string contact_no, string city=""):Employee(Eid, age, name, contact_no, city)
    {
        fstream doctor_details, doctor_id;

        doctor_id.open("Doctor_ID.txt");
        doctor_id>>Eid;
        doctor_id.close();

        doctor_id.open("Doctor_ID.txt");
        Eid++;
        doctor_id<<Eid;
        doctor_id.close();

        doctor_details.open("Doctors.txt", ios::app);
        doctor_details<<"\t\t\t\t\t\t"<<Eid<<"\t   "<<name<<"\t   "<<age<<"\t   "<<contact_no<<"\t   "<<city<<"\n";
        doctor_details.close();
    }
    friend void editPrescription(Doctor &,Patient &);
};

class Patient
{
private:
    PATIENT p;

public:
    //Parameterized constructor
    Patient(int Pid, int age, string name, string contact_no, string city="", string prescription="", int Eid=NULL)
    {
        p.Pid=Pid;
        p.age=age;
        p.name=name;
        p.city=city;
        p.contact_no=contact_no;
        p.prescription=prescription;
        p.Eid=Eid;

        fstream patient_details, patient_id;

        patient_id.open("Patient_ID.txt");
        patient_id>>Pid;
        patient_id.close();

        patient_id.open("Patient_ID.txt");
        Pid++;
        patient_id<<Pid;
        patient_id.close();

        patient_details.open("Patients.txt", ios::app);
        patient_details<<"\t\t\t\t\t\t"<<Pid<<"\t   "<<name<<"\t   "<<age<<"\t   "<<contact_no<<"\t   "<<city<<"\t   "<<prescription<<"\n";
        patient_details.close();
    }

    //Parameterless constructor
    Patient()
    {
        p.Pid=NULL;
        p.age=NULL;
        p.name="";
        p.city="";
        p.contact_no="";
        p.prescription="";
        p.Eid=NULL;
    }

    friend void editPrescription(Doctor &,Patient &);
};


void editPrescription(Doctor &d ,Patient &p)
{
    int choice;
    string presc;

    p.p.Eid=d.e.Eid;

    cout<<endl<<"1.Extend Prescription"<<endl<<"2.New Prescription"<<endl<<"Enter your choice: ";
    cin>>choice;

    switch(choice)
    {
        case 1:
            cout<<endl<<"Enter extended prescription: ";
            cin>>presc;

            p.p.prescription = p.p.prescription + ", " + presc;

            break;

        case 2:
            cout<<endl<<"Enter Prescription: ";
            cin>>presc;

            p.p.prescription = presc;

            break;

        default:
            cout<<endl<<"Invalid choice";
    }
}


int main()
{
    int choice, age, pid, eid;
    string name, contact_no, city, prescription;
    bool oldCase;

    MainMenuLabel:
    system("cls");
    cout<<"\t\t\t\t\t\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1 Welcome to Hospital Management System \xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1"<<endl<<endl;
    cout<<"\t\t\t\t\t\t\t\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1 Main Menu \xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1"<<endl<<endl;
    cout<<"\t\t\t\t\t\t\t\t1.Patient"<<endl<<"\t\t\t\t\t\t\t\t2.Doctor"<<endl<<"\t\t\t\t\t\t\t\t3.Delete All Records"<<endl<<"\t\t\t\t\t\t\t\t4.Exit"<<endl;
    cout<<endl<<"\t\t\t\t\t\t\t  Enter your choice: ";
    cin>>choice;

    switch(choice)
    {
        case 1:
            {
                PatientLabel:
                system("cls");
                cout<<"\t\t\t\t\t\t\t\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1 Patient \xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1"<<endl<<endl;
                cout<<"\t\t\t\t\t\t\t\t1.New Case"<<endl<<"\t\t\t\t\t\t\t\t2.View list"<<endl<<"\t\t\t\t\t\t\t\t3.Goto main menu"<<endl;
                cout<<endl<<"\t\t\t\t\t\t\t  Enter your choice: ";
                cin>>choice;

                switch(choice)
                {
                    case 1:
                        {
                            int id;
                            fflush(stdin);
                            cout<<endl<<"Name: ";
                            getline(cin, name);
                            cout<<"Age: ";
                            cin>>age;
                            cout<<"Contact No.: ";
                            cin>>contact_no;
                            cout<<"City: ";
                            cin>>city;
                            cout<<"Prescription: ";
                            fflush(stdin);
                            getline(cin, prescription);

                            fstream patient_id;
                            patient_id.open("Patient_ID.txt");
                            patient_id>>id;
                            patient_id.close();

                            Patient p(id, age, name, contact_no, city, prescription);

                            cout<<endl<<"Patient successfully added!!!";

                            if(ContinuePermission())
                                goto PatientLabel;
                            else
                                break;
                        }
                    case 2:
                        {
                            fstream file_data;
                            string data;
                            file_data.open("Patients.txt");
                            cout<<endl<<endl;
                            cout<<"\t\t\t\t\t\tID\t    Name\t\t   Age     Contact No\t   City\t           Prescription"<<endl;
                            getline(file_data, data, '\0');
                            cout<<data;
                            if(ContinuePermission())
                                goto DoctorLabel;
                            else
                                break;
                        }
                    case 3:
                        {
                            goto MainMenuLabel;
                        }
                }
            }
        case 2:
            {
                DoctorLabel:
                system("cls");
                cout<<"\t\t\t\t\t\t\t\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1 Doctor \xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1"<<endl<<endl;
                cout<<"\t\t\t\t\t\t\t\t1.New Entry"<<endl<<"\t\t\t\t\t\t\t\t2.View list"<<endl<<"\t\t\t\t\t\t\t\t3.Goto main menu"<<endl;
                cout<<endl<<"\t\t\t\t\t\t\t  Enter your choice: ";
                cin>>choice;

                switch(choice)
                {
                    case 1:
                        {
                            int id;
                            fflush(stdin);
                            cout<<endl<<"Name: ";
                            getline(cin, name);
                            cout<<"Age: ";
                            cin>>age;
                            fflush(stdin);
                            cout<<"Contact No.: ";
                            cin>>contact_no;
                            fflush(stdin);
                            cout<<"City: ";
                            cin>>city;

                            fstream doctor_id;
                            doctor_id.open("Doctor_ID.txt");
                            doctor_id>>id;
                            doctor_id.close();

                            Doctor d(id, age, name, contact_no, city);

                            cout<<endl<<"Doctor successfully added!!!";

                            if(ContinuePermission())
                                goto DoctorLabel;
                            else
                                break;
                        }
                    case 2:
                        {
                            fstream file_data;
                            string data;
                            file_data.open("Doctors.txt");
                            cout<<endl<<endl;
                            cout<<"\t\t\t\t\t\tID\t   Name\t           Age     Contact No\t   City"<<endl;
                            getline(file_data, data, '\0');
                            cout<<data;
                            if(ContinuePermission())
                                goto DoctorLabel;
                            else
                                break;

                        }
                    case 3:
                        {
                            goto MainMenuLabel;
                        }

                }

            }
        case 3:
            {
                fstream pat_id, pat_details, doc_id, doc_details;

                pat_id.open("Patient_ID.txt");
                pat_id<<0;
                pat_id.close();

                pat_details.open("Patients.txt");
                pat_details<<"";
                pat_details.close();

                doc_id.open("Doctor_ID.txt");
                doc_id<<0;
                doc_id.close();

                doc_details.open("Doctors.txt");
                doc_details<<"";
                doc_details.close();

                if(ContinuePermission())
                    goto MainMenuLabel;
                else
                    break;
            }
        case 4:
            {
                cout<<endl<<endl<<"\t\t\t\t\t\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1 Good Bye \xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1\xB1"<<endl<<endl;
                getch();
                exit(0);
            }
    }
}
