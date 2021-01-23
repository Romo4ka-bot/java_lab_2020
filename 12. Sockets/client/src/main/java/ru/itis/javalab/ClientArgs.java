package ru.itis.javalab;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ClientArgs {

    @Parameter(names = {"--server-ip"})
    public String serverIp;

    @Parameter(names = {"--port"})
    public int port;
}