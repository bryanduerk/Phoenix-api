package com.ctre.phoenix.motorcontrol.can;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.MotControllerJNI;
import com.ctre.phoenix.ErrorCollection;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.SensorTerm;

import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;
/**
 * CTRE Talon SRX Motor Controller when used on CAN Bus.
 */
public class TalonSRX extends com.ctre.phoenix.motorcontrol.can.BaseMotorController
		implements IMotorControllerEnhanced {

	public TalonSRX(int deviceNumber) {
		super(deviceNumber | 0x02040000);
		HAL.report(tResourceType.kResourceType_CANTalonSRX, deviceNumber + 1);
	}
	/**
	 * Sets the period of the given status frame.
	 *
	 * User ensure CAN Bus utilization is not high.
	 *
	 * This setting is not persistent and is lost when device is reset.
	 * If this is a concern, calling application can use HasReset()
	 * to determine if the status frame needs to be reconfigured.
	 *
	 * @param frame
	 *            Frame whose period is to be changed.
	 * @param periodMs
	 *            Period in ms for the given frame.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for
	 *            config success and report an error if it times out.
	 *            If zero, no blocking or checking is performed.
	 * @return Error Code generated by function. 0 indicates no error.
	 */
	public ErrorCode setStatusFramePeriod(StatusFrameEnhanced frame, int periodMs, int timeoutMs) {
		return super.setStatusFramePeriod(frame.value, periodMs, timeoutMs);
	}
	public ErrorCode setStatusFramePeriod(StatusFrameEnhanced frame, int periodMs) {
		int timeoutMs = 0;
		return setStatusFramePeriod(frame, periodMs, timeoutMs);
	}
	/**
	 * Gets the period of the given status frame.
	 *
	 * @param frame
	 *            Frame to get the period of.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for
	 *            config success and report an error if it times out.
	 *            If zero, no blocking or checking is performed.
	 * @return Period of the given status frame.
	 */
	public int getStatusFramePeriod(StatusFrameEnhanced frame, int timeoutMs) {

		return super.getStatusFramePeriod(frame, timeoutMs);
	}
	public int getStatusFramePeriod(StatusFrameEnhanced frame) {
		int timeoutMs = 0;
		return getStatusFramePeriod(frame, timeoutMs);
	}
	/**
	 * Configures the period of each velocity sample.
	 * Every 1ms a position value is sampled, and the delta between that sample
	 * and the position sampled kPeriod ms ago is inserted into a filter.
	 * kPeriod is configured with this function.
	 *
	 * @param period
	 *            Desired period for the velocity measurement. @see
	 *            #VelocityMeasPeriod
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for
	 *            config success and report an error if it times out.
	 *            If zero, no blocking or checking is performed.
	 * @return Error Code generated by function. 0 indicates no error.
	 */
	public ErrorCode configVelocityMeasurementPeriod(VelocityMeasPeriod period, int timeoutMs) {
		return super.configVelocityMeasurementPeriod(period, timeoutMs);
	}
	public ErrorCode configVelocityMeasurementPeriod(VelocityMeasPeriod period) {
		int timeoutMs = 0;	
		return configVelocityMeasurementPeriod(period, timeoutMs);
	}
	/**
	 * Sets the number of velocity samples used in the rolling average velocity
	 * measurement.
	 *
	 * @param windowSize
	 *            Number of samples in the rolling average of velocity
	 *            measurement. Valid values are 1,2,4,8,16,32. If another
	 *            value is specified, it will truncate to nearest support value.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for
	 *            config success and report an error if it times out.
	 *            If zero, no blocking or checking is performed.
	 * @return Error Code generated by function. 0 indicates no error.
	 */
	public ErrorCode configVelocityMeasurementWindow(int windowSize, int timeoutMs) {
		return super.configVelocityMeasurementWindow(windowSize, timeoutMs);
	}
	public ErrorCode configVelocityMeasurementWindow(int windowSize) {
		int timeoutMs = 0;
		return configVelocityMeasurementWindow(windowSize, timeoutMs);
	}
	/**
	 * Configures a limit switch for a local/remote source.
	 *
	 * For example, a CAN motor controller may need to monitor the Limit-R pin
	 * of another Talon, CANifier, or local Gadgeteer feedback connector.
	 *
	 * If the sensor is remote, a device ID of zero is assumed.
	 * If that's not desired, use the four parameter version of this function.
	 *
	 * @param type
	 *            Limit switch source.
	 *            User can choose between the feedback connector, remote Talon SRX, CANifier, or deactivate the feature.
	 * @param normalOpenOrClose
	 *            Setting for normally open, normally closed, or disabled. This setting
	 *            matches the web-based configuration drop down.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for
	 *            config success and report an error if it times out.
	 *            If zero, no blocking or checking is performed.
	 * @return Error Code generated by function. 0 indicates no error.
	 */
	public ErrorCode configForwardLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose,
			int timeoutMs) {

		return super.configForwardLimitSwitchSource(type.value, normalOpenOrClose.value, 0x00000000, timeoutMs);
	}
	public ErrorCode configForwardLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose) {
		int timeoutMs = 0;
		return configForwardLimitSwitchSource(type, normalOpenOrClose, timeoutMs);
	}
	/**
	 * Configures a limit switch for a local/remote source.
	 *
	 * For example, a CAN motor controller may need to monitor the Limit-R pin
	 * of another Talon, CANifier, or local Gadgeteer feedback connector.
	 *
	 * If the sensor is remote, a device ID of zero is assumed. If that's not
	 * desired, use the four parameter version of this function.
	 *
	 * @param type
	 *            Limit switch source. @see #LimitSwitchSource User can choose
	 *            between the feedback connector, remote Talon SRX, CANifier, or
	 *            deactivate the feature.
	 * @param normalOpenOrClose
	 *            Setting for normally open, normally closed, or disabled. This
	 *            setting matches the web-based configuration drop down.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for config
	 *            success and report an error if it times out. If zero, no
	 *            blocking or checking is performed.
	 * @return Error Code generated by function. 0 indicates no error.
	 */
	public ErrorCode configReverseLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose,
			int timeoutMs) {
		return super.configReverseLimitSwitchSource(type.value, normalOpenOrClose.value, 0x00000000, timeoutMs);
	}
	public ErrorCode configReverseLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose) {
		int timeoutMs = 0;
		return configReverseLimitSwitchSource(type, normalOpenOrClose, timeoutMs);

	}

	// ------ Current Lim ----------//
	/**
	 * Configure the peak allowable current (when current limit is enabled).
	 * 
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
	 *
	 * For simpler current-limiting (single threshold) use
	 * ConfigContinuousCurrentLimit() and set the peak to zero:
	 * ConfigPeakCurrentLimit(0).
	 * 
	 * @param amps
	 *            Amperes to limit.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for config
	 *            success and report an error if it times out. If zero, no
	 *            blocking or checking is performed.
	 */
	public ErrorCode configPeakCurrentLimit(int amps, int timeoutMs) {
		int retval =  MotControllerJNI.ConfigPeakCurrentLimit(m_handle, amps, timeoutMs);
		return ErrorCode.valueOf(retval);
	}
	public ErrorCode configPeakCurrentLimit(int amps) {
		int timeoutMs = 0;
		return configPeakCurrentLimit( amps,  timeoutMs);
	}

	/**
	 * Configure the peak allowable duration (when current limit is enabled).
	 *
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
	 *
	 * For simpler current-limiting (single threshold) use
	 * ConfigContinuousCurrentLimit() and set the peak to zero:
	 * ConfigPeakCurrentLimit(0).
	 * 
	 * @param milliseconds
	 *            How long to allow current-draw past peak limit.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for config
	 *            success and report an error if it times out. If zero, no
	 *            blocking or checking is performed.
	 */
	public ErrorCode configPeakCurrentDuration(int milliseconds, int timeoutMs) {
		int retval = MotControllerJNI.ConfigPeakCurrentDuration(m_handle, milliseconds, timeoutMs);
		return ErrorCode.valueOf(retval);
	}
	public ErrorCode configPeakCurrentDuration(int milliseconds) {
		int timeoutMs = 0;
		return configPeakCurrentDuration( milliseconds,  timeoutMs);
	}

	/**
	 * Configure the continuous allowable current-draw (when current limit is
	 * enabled).
	 *
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
	 *
	 * For simpler current-limiting (single threshold) use
	 * ConfigContinuousCurrentLimit() and set the peak to zero:
	 * ConfigPeakCurrentLimit(0).
	 * 
	 * @param amps
	 *            Amperes to limit.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for config
	 *            success and report an error if it times out. If zero, no
	 *            blocking or checking is performed.
	 */
	public ErrorCode configContinuousCurrentLimit(int amps, int timeoutMs) {
		int retval =  MotControllerJNI.ConfigContinuousCurrentLimit(m_handle, amps, timeoutMs);
		return ErrorCode.valueOf(retval);
	}
	public ErrorCode configContinuousCurrentLimit(int amps) {
		int timeoutMs = 0;
		return configContinuousCurrentLimit( amps,  timeoutMs); 	
	}

	/**
	 * Enable or disable Current Limit.
	 * 
	 * @param enable
	 *            Enable state of current limit.
	 * @see configPeakCurrentLimit, configPeakCurrentDuration,
	 *      configContinuousCurrentLimit
	 */
	public void enableCurrentLimit(boolean enable) {
		MotControllerJNI.EnableCurrentLimit(m_handle, enable);
	}
	
    /**
     * Configures all PID set peristant settings (overloaded so timeoutMs is 50 ms
     * and pidIdx is 0).
     *
	 * @param pid               Object with all of the PID set persistant settings
	 * @param pidIdx            0 for Primary closed-loop. 1 for auxiliary closed-loop.
     * @param timeoutMs
     *              Timeout value in ms. If nonzero, function will wait for
     *              config success and report an error if it times out.
     *              If zero, no blocking or checking is performed.
     *
     * @return Error Code generated by function. 0 indicates no error. 
     */
	public ErrorCode configurePID(TalonSRXPIDSetConfiguration pid, int pidIdx, int timeoutMs) {
        ErrorCollection errorCollection = new ErrorCollection();

        //------ sensor selection ----------//      

        errorCollection.NewError(baseConfigurePID(pid, pidIdx, timeoutMs));
        errorCollection.NewError(configSelectedFeedbackSensor(pid.selectedFeedbackSensor, pidIdx, timeoutMs));
        

        return errorCollection._worstError;
	

	}
    /**
     * Configures all PID set peristant settings (overloaded so timeoutMs is 50 ms
     * and pidIdx is 0).
     *
	 * @param pid               Object with all of the PID set persistant settings
     *
     * @return Error Code generated by function. 0 indicates no error. 
     */
	public ErrorCode configurePID(TalonSRXPIDSetConfiguration pid) {
        int pidIdx = 0;
        int timeoutMs = 50;
        return configurePID(pid, pidIdx, timeoutMs);
    }

    /**
     * Gets all PID set persistant settings.
     *
	 * @param pid               Object with all of the PID set persistant settings
	 * @param pidIdx            0 for Primary closed-loop. 1 for auxiliary closed-loop.
     * @param timeoutMs
     *              Timeout value in ms. If nonzero, function will wait for
     *              config success and report an error if it times out.
     *              If zero, no blocking or checking is performed.
     */
    public void getPIDConfigs(TalonSRXPIDSetConfiguration pid, int pidIdx, int timeoutMs)
    {
        baseGetPIDConfigs(pid, pidIdx, timeoutMs);
        pid.selectedFeedbackSensor = FeedbackDevice.valueOf(configGetParameter(ParamEnum.eFeedbackSensorType, pidIdx, timeoutMs));
    
    }
    /**
     * Gets all PID set persistant settings (overloaded so timeoutMs is 50 ms
     * and pidIdx is 0).
     *
	 * @param pid               Object with all of the PID set persistant settings
     */
	public void getPIDConfigs(TalonSRXPIDSetConfiguration pid) {
        int pidIdx = 0;
        int timeoutMs = 50;
        getPIDConfigs(pid, pidIdx, timeoutMs);
    }
	


    /**
     * Configures all peristant settings.
     *
	 * @param allConfigs        Object with all of the persistant settings
     * @param timeoutMs
     *              Timeout value in ms. If nonzero, function will wait for
     *              config success and report an error if it times out.
     *              If zero, no blocking or checking is performed.
     *
     * @return Error Code generated by function. 0 indicates no error. 
     */
	public ErrorCode configAllSettings(TalonSRXConfiguration allConfigs, int timeoutMs) {
        ErrorCollection errorCollection = new ErrorCollection();

        errorCollection.NewError(baseConfigAllSettings(allConfigs, timeoutMs));

        //------ limit switch ----------//   
        errorCollection.NewError(MotControllerJNI.ConfigForwardLimitSwitchSource(m_handle, allConfigs.forwardLimitSwitchSource.value,
                allConfigs.forwardLimitSwitchNormal.value, allConfigs.forwardLimitSwitchDeviceID, timeoutMs));
        errorCollection.NewError(MotControllerJNI.ConfigReverseLimitSwitchSource(m_handle, allConfigs.reverseLimitSwitchSource.value,
                allConfigs.reverseLimitSwitchNormal.value, allConfigs.reverseLimitSwitchDeviceID, timeoutMs));
        


        //--------PIDs---------------//

        errorCollection.NewError(configurePID(allConfigs.primaryPID, 0, timeoutMs));
        errorCollection.NewError(configurePID(allConfigs.auxilaryPID, 1, timeoutMs));
        errorCollection.NewError(configSensorTerm(SensorTerm.Sum0, allConfigs.sum_0, timeoutMs));
        errorCollection.NewError(configSensorTerm(SensorTerm.Sum1, allConfigs.sum_1, timeoutMs));
        errorCollection.NewError(configSensorTerm(SensorTerm.Diff0, allConfigs.diff_0, timeoutMs));
        errorCollection.NewError(configSensorTerm(SensorTerm.Diff1, allConfigs.diff_1, timeoutMs));
        

        //--------Current Limiting-----//
        errorCollection.NewError(configPeakCurrentLimit(allConfigs.peakCurrentLimit, timeoutMs));
        errorCollection.NewError(configPeakCurrentDuration(allConfigs.peakCurrentDuration, timeoutMs));
        errorCollection.NewError(configContinuousCurrentLimit(allConfigs.continuousCurrentLimit, timeoutMs));
        


        return errorCollection._worstError; 	

	}
	
    /**
     * Configures all peristant settings (overloaded so timeoutMs is 50 ms).
     *
	 * @param allConfigs        Object with all of the persistant settings
     *
     * @return Error Code generated by function. 0 indicates no error. 
     */
	public ErrorCode configAllSettings(TalonSRXConfiguration allConfigs) {
		int timeoutMs = 50;
		return configAllSettings(allConfigs, timeoutMs);
	}

    /**
     * Gets all persistant settings.
     *
	 * @param allConfigs        Object with all of the persistant settings
     * @param timeoutMs
     *              Timeout value in ms. If nonzero, function will wait for
     *              config success and report an error if it times out.
     *              If zero, no blocking or checking is performed.
     */
    public void getAllConfigs(TalonSRXConfiguration allConfigs, int timeoutMs) {
    
        baseGetAllConfigs(allConfigs, timeoutMs);
    
        getPIDConfigs(allConfigs.primaryPID, 0, timeoutMs);
        getPIDConfigs(allConfigs.auxilaryPID, 1, timeoutMs);
        allConfigs.sum_0 =  FeedbackDevice.valueOf(configGetParameter(ParamEnum.eSensorTerm, 0, timeoutMs));
        allConfigs.sum_1 =  FeedbackDevice.valueOf(configGetParameter(ParamEnum.eSensorTerm, 1, timeoutMs));
        allConfigs.diff_0 = FeedbackDevice.valueOf(configGetParameter(ParamEnum.eSensorTerm, 2, timeoutMs));
        allConfigs.diff_1 = FeedbackDevice.valueOf(configGetParameter(ParamEnum.eSensorTerm, 3, timeoutMs));
    
    
        allConfigs.forwardLimitSwitchSource = LimitSwitchSource.valueOf(configGetParameter(ParamEnum.eLimitSwitchSource, 0, timeoutMs));
        allConfigs.reverseLimitSwitchSource = LimitSwitchSource.valueOf(configGetParameter(ParamEnum.eLimitSwitchSource, 1, timeoutMs));
        allConfigs.forwardLimitSwitchDeviceID = (int) configGetParameter(ParamEnum.eLimitSwitchRemoteDevID, 0, timeoutMs);
        allConfigs.reverseLimitSwitchDeviceID = (int) configGetParameter(ParamEnum.eLimitSwitchRemoteDevID, 1, timeoutMs);
        allConfigs.forwardLimitSwitchNormal = LimitSwitchNormal.valueOf(configGetParameter(ParamEnum.eLimitSwitchNormClosedAndDis, 0, timeoutMs));
        allConfigs.reverseLimitSwitchNormal = LimitSwitchNormal.valueOf(configGetParameter(ParamEnum.eLimitSwitchNormClosedAndDis, 1, timeoutMs));
        allConfigs.peakCurrentLimit        = (int) configGetParameter(ParamEnum.ePeakCurrentLimitAmps, 0, timeoutMs);
        allConfigs.peakCurrentDuration     = (int) configGetParameter(ParamEnum.ePeakCurrentLimitMs, 0, timeoutMs);
        allConfigs.continuousCurrentLimit  = (int) configGetParameter(ParamEnum.eContinuousCurrentLimitAmps, 0, timeoutMs);
    
    }
    /**
     * Gets all persistant settings (overloaded so timeoutMs is 50 ms).
     *
	 * @param allConfigs        Object with all of the persistant settings
     */
    public void getAllConfigs(TalonSRXConfiguration allConfigs) {
        int timeoutMs = 50;
        getAllConfigs(allConfigs, timeoutMs);
    }



}
