package com.veritasfinance.voice;

public class VoiceService {

    public String processCommand(String command) {

        if(command == null)
            return "No command received.";

        command = command.toLowerCase();

        if(command.contains("dashboard"))
            return "Opening Dashboard";

        if(command.contains("upload"))
            return "Opening Upload Documents";

        if(command.contains("trust"))
            return "Opening Trust Score";

        if(command.contains("analytics"))
            return "Opening Analytics";

        if(command.contains("report"))
            return "Opening Reports";

        if(command.contains("privacy"))
            return "Opening Privacy Settings";

        if(command.contains("logout"))
            return "Logging Out";

        return "Command not recognized.";

    }

}