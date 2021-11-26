package com.thanhdat.yams.Models;

public class SettingMyProfile {
    private int ProfileId;
    private String ProfileName;
    private String ProfileInformation;

    public SettingMyProfile(int profileId, String profileName, String profileInformation) {
        ProfileId = profileId;
        ProfileName = profileName;
        ProfileInformation = profileInformation;
    }

    public int getProfileId() {
        return ProfileId;
    }

    public void setProfileId(int profileId) {
        ProfileId = profileId;
    }

    public String getProfileName() {
        return ProfileName;
    }

    public void setProfileName(String profileName) {
        ProfileName = profileName;
    }

    public String getProfileInformation() {
        return ProfileInformation;
    }

    public void setProfileInformation(String profileInformation) {
        ProfileInformation = profileInformation;
    }
}
