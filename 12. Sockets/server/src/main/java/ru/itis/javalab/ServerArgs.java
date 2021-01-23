package ru.itis.javalab;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ServerArgs {

    @Parameter(names = {"--port"})
    public int port;
}