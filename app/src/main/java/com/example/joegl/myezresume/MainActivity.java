package com.example.joegl.myezresume;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joegl.myezresume.model.BasicInfo;
import com.example.joegl.myezresume.model.Education;
import com.example.joegl.myezresume.model.Experience;
import com.example.joegl.myezresume.model.Project;
import com.example.joegl.myezresume.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    private BasicInfo basicInfo;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Project> projects;

    private static final String MODEL_EDUCATIONS = "educations";
    private static final String MODEL_EXPERIENCES = "experiences";
    private static final String MODEL_PROJECTS = "projects";
    private static final String MODEL_BASIC_INFO = "basic_info";

    private static final int REQ_CODE_EDIT_EDUCATION = 100;
    private static final int REQ_CODE_EDIT_EXPERIENCE = 101;
    private static final int REQ_CODE_EDIT_PROJECT = 102;
    private static final int REQ_CODE_EDIT_BASIC_INFO = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fakeData();
        setupUI();

    }

    private void setupUI() {
        setContentView(R.layout.activity_main);


        ImageButton addEducationBtn = (ImageButton) findViewById(R.id.add_education_button);
        addEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDIT_EDUCATION);
            }
        });

        ImageButton addProjectBtn = (ImageButton) findViewById(R.id.add_project_button);
        addProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 不要跳错坑！！！
                Intent intent = new Intent(MainActivity.this, ProjectEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDIT_PROJECT);
            }
        });

        ImageButton addExperienceBtn = (ImageButton) findViewById(R.id.add_experience_button);
        addExperienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExperienceEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDIT_EXPERIENCE);
            }
        });

        setupBasicInfo();
        setupEducations();
        setupProjects();
        setupExperiences();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Log.i("JoeLiaoDemo", "ok");
            switch (requestCode) {
                case REQ_CODE_EDIT_BASIC_INFO:
                    //BasicInfo basicInfo = data.getParcelableExtra(BasicInfoEditActivity.KEY_BASIC_INFO);
                    updateBasicInfo();
                    break;

                case REQ_CODE_EDIT_EDUCATION:
                    String educationId = data.getStringExtra(EducationEditActivity.KEY_EDUCATION_ID);
                    if (educationId != null) {
                        //delete button clicked
                        deleteEducation(educationId);
                    } else {
                        Education education = data.getParcelableExtra(EducationEditActivity.KEY_EDUCATION);
                        updateEducation(education);
                    }
                    break;

                case REQ_CODE_EDIT_PROJECT:
                    String projectId = data.getStringExtra(ProjectEditActivity.KEY_PROJECT_ID);
                    if (projectId != null) {
                        //delete button clicked
                        deleteProject(projectId);
                    } else {
                        Project project = data.getParcelableExtra(ProjectEditActivity.KEY_PROJECT);
                        updateProject(project);
                    }
                    break;

                case REQ_CODE_EDIT_EXPERIENCE:
                    String experienceId = data.getStringExtra(ExperienceEditActivity.KEY_EXPERIENCE_ID);
                    if (experienceId != null) {
                        //delete button clicked
                        deleteExperience(experienceId);
                    } else {
                        Experience experience = data.getParcelableExtra(ExperienceEditActivity.KEY_EXPERIENCE);
                        updateExperience(experience);
                    }
                    break;
            }
        }


    }

    private void setupBasicInfo() {
        ((TextView) findViewById(R.id.name)).setText(basicInfo.name);
        ((TextView) findViewById(R.id.email)).setText(basicInfo.email);
    }


    private void setupEducations() {

        LinearLayout educationsLayout = (LinearLayout) findViewById(R.id.education_list);
        educationsLayout.removeAllViews();
        for (Education education : educations) {
            View educationView = getLayoutInflater().inflate(R.layout.education_item, null);
            setupEducation(educationView, education);
            educationsLayout.addView(educationView);
        }
    }

    private void setupEducation(View educationView, final Education education) {
        String dateString = DateUtil.dateToString(education.startDate)
                + " ~ " + DateUtil.dateToString(education.endDate);
        ((TextView) educationView.findViewById(R.id.education_school)).setText(education.school +
                " (" + dateString + ") \n" + "Major : " + education.major);
        ((TextView) educationView.findViewById(R.id.education_courses))
                .setText(formatItem(education.courses));

        // add onClickListener on edit button
        ImageButton editEducationBtn = (ImageButton) educationView.findViewById(R.id.edit_education_btn);
        editEducationBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // transport the existing data to EducationEditActivity
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                intent.putExtra(EducationEditActivity.KEY_EDUCATION, education);
                startActivityForResult(intent, REQ_CODE_EDIT_EDUCATION);
            }
        });
    }



    private void setupProjects() {
        LinearLayout projectsLayout = (LinearLayout) findViewById(R.id.project_list);
        projectsLayout.removeAllViews();
        for (Project project : projects) {
            View projectView = getLayoutInflater().inflate(R.layout.project_item, null);
            setupProject(projectView, project);
            projectsLayout.addView(projectView);
        }
    }

    private void setupProject(@NonNull View projectView, final Project project) {
        String dateString = DateUtil.dateToString(project.startDate)
                + " ~ " + DateUtil.dateToString(project.endDate);
        ((TextView) projectView.findViewById(R.id.project_title)).setText(project.projectName +
                " (" + dateString + ")" );
        ((TextView) projectView.findViewById(R.id.project_description))
                .setText(formatItem(project.details));

        ImageButton editProjectBtn = (ImageButton) projectView.findViewById(R.id.edit_project_btn);
        editProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProjectEditActivity.class);
                intent.putExtra(ProjectEditActivity.KEY_PROJECT, project);
                startActivityForResult(intent, REQ_CODE_EDIT_PROJECT);
            }
        });
    }

    private void setupExperiences() {
        LinearLayout experiencesLayout = (LinearLayout) findViewById(R.id.experience_list);
        experiencesLayout.removeAllViews();
        for (Experience experience : experiences) {
            View experienceView = getLayoutInflater().inflate(R.layout.experience_item, null);
            setupExperience(experienceView, experience);
            experiencesLayout.addView(experienceView);
        }
    }
    private void setupExperience(View experienceView, final Experience experience) {
        String dateString = DateUtil.dateToString(experience.startDate)
                + " ~ " + DateUtil.dateToString(experience.endDate);
        ((TextView) experienceView.findViewById(R.id.experience_title)).setText(experience.companyName +
                " (" + dateString + ") \n" + "Title : " + experience.title);
        ((TextView) experienceView.findViewById(R.id.experience_description))
                .setText(formatItem(experience.details));

        experienceView.findViewById(R.id.edit_experience_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExperienceEditActivity.class);
                intent.putExtra(ExperienceEditActivity.KEY_EXPERIENCE, experience);
                startActivityForResult(intent, REQ_CODE_EDIT_EXPERIENCE);
            }
        });
    }

    private void updateBasicInfo() {

    }

    private void updateEducation(Education education) {
        boolean found = false;
        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            if (TextUtils.equals(e.id, education.id)) {
                found = true;
                educations.set(i, education);
                break;
            }
        }
        if (!found) {
            educations.add(education);
        }
        setupEducations();
    }

    private void deleteEducation(@NonNull String educationId) {

        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            if (TextUtils.equals(e.id, educationId)) {
                educations.remove(i);
                break;
            }
        }
        setupEducations();
    }

    private void updateProject(Project project) {
        boolean found = false;
        for (int i = 0; i < projects.size(); i++) {
            Project p = projects.get(i);
            if (TextUtils.equals(p.id, project.id)) {
                found = true;
                projects.set(i, project);
                break;
            }
        }
        if (!found) {
            projects.add(project);
        }
        setupProjects();
    }

    private void deleteProject(@NonNull String projectId) {
        for (int i = 0; i < projects.size(); i++) {
            Project p = projects.get(i);
            if (TextUtils.equals(p.id, projectId)) {
                projects.remove(i);
                break;
            }
        }
        setupProjects();
    }

    private void updateExperience(Experience experience) {
        boolean found = false;
        for (int i = 0; i < experiences.size(); i++) {
            Experience e = experiences.get(i);
            if (TextUtils.equals(e.id, experience.id)) {
                found = true;
                experiences.set(i, experience);
                break;
            }
        }
        if (!found) {
            experiences.add(experience);
        }
        setupExperiences();
    }

    private void deleteExperience(@NonNull String experienceID) {
        for (int i = 0; i < experiences.size(); i++) {
            Experience e = experiences.get(i);
            if (TextUtils.equals(e.id, experienceID)) {
                experiences.remove(i);
                break;
            }
        }
        setupExperiences();
    }



    public static String formatItem(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(" - ").append(item).append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private void loadData() {}

    private void fakeData() {
        basicInfo = new BasicInfo();
        basicInfo.name = "Joe Liao";
        basicInfo.email = "joeglancith@hotmail.com";

        Education education1 = new Education();
        education1.school = "MTU";
        education1.major = "Electrical Engineering";
        education1.startDate = DateUtil.stringToDate("09/2015");
        education1.endDate = DateUtil.stringToDate("12/2016");
        education1.courses = new ArrayList<>();
        education1.courses.add("Data Structure");
        education1.courses.add("Algorithms");
        education1.courses.add("Wireless Sensor Network");

        Education education2 = new Education();
        education2.school = "SHU";
        education2.major = "Communication Engineering";
        education2.startDate = DateUtil.stringToDate("09/2010");
        education2.endDate = DateUtil.stringToDate("07/2014");
        education2.courses = new ArrayList<>();
        education2.courses.add("C++");
        education2.courses.add("DataBase");

        educations = new ArrayList<>();
        educations.add(education1);
        educations.add(education2);

        projects = new ArrayList<>();
        Project project = new Project();
        project.projectName = "111";
        project.startDate = DateUtil.stringToDate("09");
        project.endDate = DateUtil.stringToDate("1");
        project.details = new ArrayList<>();
        project.details.add("111");
        project.details.add("222");
        projects.add(project);

        experiences = new ArrayList<>();

    }


}
