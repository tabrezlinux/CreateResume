package com.example.tabrezahmad.createresume;

public class ResumeListModel {

    private int _id;
    private String resume_list_name, resume_list_fname;
    private int iv_resume_list;
//    private int ib_resume_list_copy;
//    private int ib_resume_list_edit;
//    private int ib_resume_list_delete;

    public ResumeListModel(String resume_list_name, String resume_list_fname, int iv_resume_list) {
        this.resume_list_name = resume_list_name;
        this.resume_list_fname = resume_list_fname;
        this.iv_resume_list = iv_resume_list;
//        this.ib_resume_list_copy = ib_resume_list_copy;
//        this.ib_resume_list_edit = ib_resume_list_edit;
//        this.ib_resume_list_delete = ib_resume_list_delete;
//        this._id = _id;
    }

//    public int getid() {
//        return _id;
//    }

    public String getResume_list_name() {
        return resume_list_name;
    }

    public String getResume_list_fname() {
        return resume_list_fname;
    }

    public int getIv_resume_list() {
        return iv_resume_list;
    }

//    public int getIb_resume_list_copy() {
//        return ib_resume_list_copy;
//    }
//
//    public int getIb_resume_list_edit() {
//        return ib_resume_list_edit;
//    }
//
//    public int getIb_resume_list_delete() {
//        return ib_resume_list_delete;
//    }
}
