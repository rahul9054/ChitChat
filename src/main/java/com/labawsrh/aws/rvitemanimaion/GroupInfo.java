package com.labawsrh.aws.rvitemanimaion;

class GroupInfo {
    String name ;

    public GroupInfo(String name, String headline) {
        this.name = name;
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    String headline ;

    public GroupInfo() {
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }


}
