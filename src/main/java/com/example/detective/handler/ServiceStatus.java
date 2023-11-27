package com.example.detective.handler;

import lombok.Getter;

@Getter
public enum ServiceStatus {

    //General
    SUCCESS(0, "Success!"),

    //User
    USER_NOT_FOUND(100, "User not found"),
    USERNAME_ALREADY_EXIST(101, "Username already exists"),
    USER_DOES_NOT_EXIST(102, "User does not exist (Invalid User ID)"),
    USERNAME_EMPTY(103, "Username can't be empty"),
    DISPLAYNAME_EMPTY(104, "Name can't be empty"),
    FOLLOW_YOURSELF(105, "You can't follow yourself"),
    FOLLOW_DOES_NOT_EXIST(106, "You can't follow non existing user"),
    FOLLOWER_DOES_NOT_EXIST(107, "You can't follow if you do not exist"),
    FOLLOWED_ALREADY(108, "User already followed"),
    NOT_FOLLOWED(109, "User not followed"),

    //Post
    POST_NOT_FOUND(200, "Post not found"),
    POST_DOES_NOT_EXIST(201, "Post does not exist (Invalid Post ID)"),
    POST_USER_EMPTY(202, "Post's user can't be empty"),
    POST_CONTENT_EMPTY(203, "Post's content can't be empty"),
    POST_LIKED_ALREADY(204, "Post already liked"),
    POST_NOT_LIKED(205, "Post not liked"),

    //Comment
    COMMENT_NOT_FOUND(300, "Comment not found"),
    COMMENT_DOES_NOT_EXIST(301, "Comment does not exist (Invalid Comment ID)"),
    COMMENT_USER_EMPTY(302, "Comment's user can't be empty"),
    COMMENT_POST_EMPTY(303, "Comment's post can't be empty"),
    COMMENT_CONTENT_EMPTY(304, "Comment's content can't be empty"),
    COMMENT_LIKED_ALREADY(305, "Comment already liked"),
    COMMENT_NOT_LIKED(306, "Comment not liked"),

    //Error
    ERROR(400, "An unknown error occurred"),
    UNAUTHORIZED(401, "You are not authorized for this action"), 


    
    INCIDENT_NOT_FOUND (500, "incident not found"), 
    
    INVALID_INPUT(501, "Incorrect password or username"), 
    
    INVALID_OTP(502, "otp has expired or you entered incorrect values"), 
    
    AUTHENTICATION_FAILED(503, "One of the values is incorrect"), 
    
    INVALID_REPORT_STATUS (504, "error in report status"), 
    
    SUPPORT_ORDER_NOT_FOUND(505, "support order not found"),
    
    REPORT_NOT_FOUND(506, "Report cannot be found."), 
    
    CHANGE_NOT_ALLOWED(507, "Cannot change the status of a non-new report."), 
    
    UNCOFIRMED(508, "Reporting or Investigator must first be confirmed by the detectives."), 
    
    STATUS_ERROR(510, "Unknown status change."), 
    
    LOW_BALANCE(511, "insufficient balance"), 
    
    INCIDENT_ERROR(512, "Could not retrieve incident."), 
    
    REPORT_ERROR(513, "Cannot follow up first time reports."), 
    
    REPORT_INCOMPLETE (514, "report is either not completed or has invalid status."), 
    INCIDENT_TYPE_NOT_FOUND (515, "Incident Type could not be found"),
    INCIDENTS_NOT_FOUND (516, "incidents not found"), ;

    private final int code;
    private final String message;

    private ServiceStatus(int code, String message){
        this.code = code;
        this.message = message;
    }

    String getMessage() {
        return message;
    }

    int getCode() {
        return code;
    }
}