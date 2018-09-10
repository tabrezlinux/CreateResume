package com.example.tabrezahmad.createresume;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.tabrezahmad.createresume.database.FormValidator;

public class SkillAndAchievementFrag extends Fragment {
    private EditText et_achievement,
            et_skill,
            et_hobby,
            et_activities;

    private TextInputLayout tl_achievement,
            tl_skill,
            tl_hobby,
            tl_activities;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.achievements_awards, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();
    }

    private void setupViews() {

        //setup fields
        et_achievement = root.findViewById(R.id.et_achievement);
        et_skill = root.findViewById(R.id.et_skill);
        et_hobby = root.findViewById(R.id.et_hobby);
        et_activities = root.findViewById(R.id.et_activities);

        //setup layouts
        tl_achievement = root.findViewById(R.id.tl_achievement);
        tl_skill= root.findViewById(R.id.tl_skill);
        tl_hobby = root.findViewById(R.id.tl_hobby);
        tl_activities = root.findViewById(R.id.tl_activities);

        //-------------------------------------------------------------------------------Achievement
        //Not null or empty, 2-80 Char, save data
        et_achievement.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code){
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_achievement.setError("Required");
                        break;

                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_achievement.setError("4-80 Char Required");
                        break;

                    default:
                        tl_achievement.setError(null);

                }

            }

            @Override
            public void onSuccess(String s) {
                tl_achievement.setError(null);
                NewResumeActivity.ACHIEVEMENT_AWARD.get(0).achievement_awards = s;

            }
        },2,80));


        //-------------------------------------------------------------------------------------Skill
        //Not null or empty, 2-80 Char, save data
        et_skill.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code){
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_skill.setError("Required");
                        break;

                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_skill.setError("4-80 Char Required");
                        break;

                    default:
                        tl_skill.setError(null);

                }

            }

            @Override
            public void onSuccess(String s) {
                tl_skill.setError(null);
                NewResumeActivity.SKILL.get(0).skills = s;

            }
        },2,80));

        //-------------------------------------------------------------------------------------Hobby
        //Not null or empty, 2-80 Char, save data
        et_hobby.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code){
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_hobby.setError("Required");
                        break;

                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_hobby.setError("4-80 Char Required");
                        break;

                    default:
                        tl_hobby.setError(null);

                }

            }

            @Override
            public void onSuccess(String s) {
                tl_hobby.setError(null);
                NewResumeActivity.HOBBY.get(0).hobby = s;

            }
        },2,80));

        //--------------------------------------------------------------------------------Activities
        //Not null or empty, 2-80 Char, save data
        et_activities.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code){
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_activities.setError("Required");
                        break;

                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_activities.setError("4-80 Char Required");
                        break;

                    default:
                        tl_activities.setError(null);

                }

            }

            @Override
            public void onSuccess(String s) {
                tl_activities.setError(null);
                NewResumeActivity.ACTIVITIES.get(0).activity = s;

            }
        },2,80));

    }
}
