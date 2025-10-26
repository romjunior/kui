package com.kui.platform

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy
import ch.qos.logback.core.util.FileSize
import org.slf4j.LoggerFactory
import java.io.File

object LoggingService {
    
    fun configureLogging(configPath: String) {
        val context = LoggerFactory.getILoggerFactory() as LoggerContext
        context.reset()
        
        val rootLogger = context.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME)
        
        // Console Appender
        val consoleAppender = ConsoleAppender<ILoggingEvent>().apply {
            this.context = context
            name = "CONSOLE"
            encoder = PatternLayoutEncoder().apply {
                this.context = context
                pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
                start()
            }
            start()
        }
        
        // File Appender
        val logFile = File(configPath, "kui.log")
        val fileAppender = RollingFileAppender<ILoggingEvent>()
        fileAppender.context = context
        fileAppender.name = "FILE"
        fileAppender.file = logFile.absolutePath
        
        val encoder = PatternLayoutEncoder()
        encoder.context = context
        encoder.pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
        encoder.start()
        fileAppender.encoder = encoder
        
        val rollingPolicy = SizeAndTimeBasedRollingPolicy<ILoggingEvent>()
        rollingPolicy.context = context
        rollingPolicy.setParent(fileAppender)
        rollingPolicy.fileNamePattern = "${logFile.absolutePath}.%d{yyyy-MM-dd}.%i.gz"
        rollingPolicy.setMaxFileSize(FileSize.valueOf("10MB"))
        rollingPolicy.maxHistory = 30
        rollingPolicy.setTotalSizeCap(FileSize.valueOf("100MB"))
        rollingPolicy.start()
        
        fileAppender.rollingPolicy = rollingPolicy
        fileAppender.start()
        
        rootLogger.addAppender(consoleAppender)
        rootLogger.addAppender(fileAppender)
        rootLogger.level = ch.qos.logback.classic.Level.INFO
    }
}