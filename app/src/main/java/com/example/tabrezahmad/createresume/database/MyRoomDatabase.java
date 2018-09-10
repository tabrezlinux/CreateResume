package com.example.tabrezahmad.createresume.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.tabrezahmad.createresume.database.Converters.Converters;
import com.example.tabrezahmad.createresume.database.DAO.AcademicQuaDAO;
import com.example.tabrezahmad.createresume.database.DAO.AchievementAwardDAO;
import com.example.tabrezahmad.createresume.database.DAO.ActivitiesDAO;
import com.example.tabrezahmad.createresume.database.DAO.AreaOfInterestDAO;
import com.example.tabrezahmad.createresume.database.DAO.BasicInfoDAO;
import com.example.tabrezahmad.createresume.database.DAO.CareerObjectiveDAO;
import com.example.tabrezahmad.createresume.database.DAO.HobbyDAO;
import com.example.tabrezahmad.createresume.database.DAO.InternshipDAO;
import com.example.tabrezahmad.createresume.database.DAO.PhotAndSignDAO;
import com.example.tabrezahmad.createresume.database.DAO.ProfessionalQuaDAO;
import com.example.tabrezahmad.createresume.database.DAO.ProjectDAO;
import com.example.tabrezahmad.createresume.database.DAO.ReferenceDAO;
import com.example.tabrezahmad.createresume.database.DAO.SkillDAO;
import com.example.tabrezahmad.createresume.database.DAO.StrengthDAO;
import com.example.tabrezahmad.createresume.database.DAO.WorkExperienceDAO;
import com.example.tabrezahmad.createresume.database.Entity.AcademicQualification;
import com.example.tabrezahmad.createresume.database.Entity.AchievementsAndAwards;
import com.example.tabrezahmad.createresume.database.Entity.Activities;
import com.example.tabrezahmad.createresume.database.Entity.AreaOfInterest;
import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.CareerObjective;
import com.example.tabrezahmad.createresume.database.Entity.Hobby;
import com.example.tabrezahmad.createresume.database.Entity.Internship;
import com.example.tabrezahmad.createresume.database.Entity.PhotoAndSign;
import com.example.tabrezahmad.createresume.database.Entity.ProfessionalQualification;
import com.example.tabrezahmad.createresume.database.Entity.Project;
import com.example.tabrezahmad.createresume.database.Entity.Reference;
import com.example.tabrezahmad.createresume.database.Entity.Skill;
import com.example.tabrezahmad.createresume.database.Entity.Strength;
import com.example.tabrezahmad.createresume.database.Entity.Template;
import com.example.tabrezahmad.createresume.database.Entity.TemplateChoice;
import com.example.tabrezahmad.createresume.database.Entity.WorkExperience;

@Database(entities = {
        BasicInfo.class,
        AcademicQualification.class,
        ProfessionalQualification.class,
        Project.class,
        Internship.class,
        WorkExperience.class,
        AchievementsAndAwards.class,
        Skill.class,
        Activities.class,
        AreaOfInterest.class,
        Template.class,
        TemplateChoice.class,
        Hobby.class,
        CareerObjective.class,
        Strength.class,
        PhotoAndSign.class,
        Reference.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract AcademicQuaDAO AcademicQuaDAO();
    public abstract AchievementAwardDAO AchievementDAO();
    public abstract ActivitiesDAO ActivitiesDAO();
    public abstract AreaOfInterestDAO AreaOfInterestDAO();
    public abstract BasicInfoDAO BasicInfoDAO();
    public abstract CareerObjectiveDAO CareerObjectiveDAO();
    public abstract HobbyDAO hobbyDAO();
    public abstract InternshipDAO InternshipDAO();
    public abstract PhotAndSignDAO PhotAndSignDAO();
    public abstract ProfessionalQuaDAO ProfessionalQuaDAO();
    public abstract ProjectDAO ProjectDAO();
    public abstract ReferenceDAO ReferenceDAO();
    public abstract SkillDAO SkillDAO();
    public abstract StrengthDAO StrengthDAO();
    public abstract WorkExperienceDAO WorkExperienceDAO();


}
