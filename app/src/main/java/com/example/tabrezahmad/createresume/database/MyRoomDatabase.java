package com.example.tabrezahmad.createresume.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.tabrezahmad.createresume.database.Converters.Converters;
import com.example.tabrezahmad.createresume.database.DAO.BasicInfoDAO;
import com.example.tabrezahmad.createresume.database.DAO.CareerObjectiveDAO;
import com.example.tabrezahmad.createresume.database.DAO.PhotoAndSignDAO;
import com.example.tabrezahmad.createresume.database.DAO.ProjectAndTrainingDAO;
import com.example.tabrezahmad.createresume.database.DAO.AcademicQualificationDAO;
import com.example.tabrezahmad.createresume.database.DAO.QualificationProfessionalDAO;
import com.example.tabrezahmad.createresume.database.DAO.ReferenceDAO;
import com.example.tabrezahmad.createresume.database.DAO.WorkExperienceDAO;
import com.example.tabrezahmad.createresume.database.Entity.AcademicQualification;
import com.example.tabrezahmad.createresume.database.Entity.AchievementsAndAwards;
import com.example.tabrezahmad.createresume.database.Entity.ActivityCurricular;
import com.example.tabrezahmad.createresume.database.Entity.ActivityExtra;
import com.example.tabrezahmad.createresume.database.Entity.AreaOfInterest;
import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.CareerObjective;
import com.example.tabrezahmad.createresume.database.Entity.Hobby;
import com.example.tabrezahmad.createresume.database.Entity.IndustrialExposure;
import com.example.tabrezahmad.createresume.database.Entity.Internship;
import com.example.tabrezahmad.createresume.database.Entity.PhotoAndSign;
import com.example.tabrezahmad.createresume.database.Entity.ProfessionalQualification;
import com.example.tabrezahmad.createresume.database.Entity.Project;
import com.example.tabrezahmad.createresume.database.Entity.Reference;
import com.example.tabrezahmad.createresume.database.Entity.Skill;
import com.example.tabrezahmad.createresume.database.Entity.Strength;
import com.example.tabrezahmad.createresume.database.Entity.TechnicalSkill;
import com.example.tabrezahmad.createresume.database.Entity.Training;
import com.example.tabrezahmad.createresume.database.Entity.WorkExperience;

@Database(entities = {
        AcademicQualification.class,
        AchievementsAndAwards.class,
        ActivityCurricular.class,
        ActivityExtra.class,
        AreaOfInterest.class,
        BasicInfo.class,
        CareerObjective.class,
        Hobby.class,
        IndustrialExposure.class,
        Internship.class,
        PhotoAndSign.class,
        ProfessionalQualification.class,
        Project.class,
        Reference.class,
        Skill.class,
        Strength.class,
        TechnicalSkill.class,
        Training.class,
        WorkExperience.class,}, version = 1)
@TypeConverters({Converters.class})
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract BasicInfoDAO BasicInfoDAO();
    public abstract AcademicQualificationDAO AcademicQualificationDAO();
    public abstract QualificationProfessionalDAO ProfessionalQualificationDAO();
    public abstract ProjectAndTrainingDAO ProjectAndTrainingDAO();
    public abstract WorkExperienceDAO WorkExperienceDAO();
    public abstract CareerObjectiveDAO CareerObjectiveDAO();
    public abstract PhotoAndSignDAO PhotoAndSignDAO();
    public abstract ReferenceDAO ReferenceDAO();

}
