package com.example.detective.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
@AllArgsConstructor
public enum ReportStatus {
    UNKNOWN, NEW, REJECTED, SUPPORT, REPORTING, CONFIRMED,  VERIFICATION;
    
    public static final String N = "N";
    public static final String J = "J";
    public static final String R = "R";
    public static final String F = "F";
    public static final String P = "P";
    public static final String E = "E";
    
        public static ReportStatus UnmarshalJSON(String b) throws JSONException {
            ReportStatus status = ReportStatus.UNKNOWN;
    
            status = switch (b.toUpperCase()) {
            case N -> ReportStatus.NEW;
            case J -> ReportStatus.REJECTED;
            case R -> ReportStatus.SUPPORT;
            case F -> ReportStatus.REPORTING;
            case P -> ReportStatus.CONFIRMED;
            case E -> ReportStatus.VERIFICATION;
            default -> ReportStatus.UNKNOWN;
        };

        return status;
        }
    
        public static String MarshalJSON(ReportStatus s) throws JSONException {
           
             String value = "";
    
            if (null == s) {
                value = "";
            } else value = switch (s) {
                 case UNKNOWN -> "";
                 case NEW -> N;
                 case REJECTED -> J;
                 case SUPPORT -> R;
                 case REPORTING -> F;
                 case CONFIRMED -> P;
                 case VERIFICATION -> E;
                 default -> "";
             }; 
    
            return JSONObject.quote(value);
        }    
}
