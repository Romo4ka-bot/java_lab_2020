package ru.itis.javalab.homework;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {

    @Parameter(names = {"--server-ip"})
    public String serverIp;

    @Parameter(names = {"--port"})
    public int port;
}