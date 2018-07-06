package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.ErrorCollection;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;

import edu.wpi.first.wpilibj.hal.HAL;
/**
 * VEX Victor SPX Motor Controller when used on CAN Bus.
 */
public class VictorSPX extends com.ctre.phoenix.motorcontrol.can.BaseMotorController
    implements IMotorController {
	/**
	 * Constructor
	 * 
	 * @param deviceNumber
	 *            [0,62]
	 */
	public VictorSPX(int deviceNumber) {
		super(deviceNumber | 0x01040000);
		HAL.report(65, deviceNumber + 1);
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
	ErrorCode configurePID(VictorSPXPIDSetConfiguration pid, int pidIdx, int timeoutMs) {
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
	ErrorCode configurePID(VictorSPXPIDSetConfiguration pid) {
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
    public void getPIDConfigs(VictorSPXPIDSetConfiguration pid, int pidIdx, int timeoutMs)
    {
        baseGetPIDConfigs(pid, pidIdx, timeoutMs);
        pid.selectedFeedbackSensor = RemoteFeedbackDevice.valueOf(configGetParameter(ParamEnum.eFeedbackSensorType, pidIdx, timeoutMs));
    
    }
    /**
     * Gets all PID set persistant settings (overloaded so timeoutMs is 50 ms
     * and pidIdx is 0).
     *
	 * @param pid               Object with all of the PID set persistant settings
     */
	public void getPIDConfigs(VictorSPXPIDSetConfiguration pid) {
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
	public ErrorCode configAllSettings(VictorSPXConfiguration allConfigs, int timeoutMs) {
	
        ErrorCollection errorCollection = new ErrorCollection();
        
        errorCollection.NewError(baseConfigAllSettings(allConfigs, timeoutMs));

        //------ remote limit switch ----------//   
        errorCollection.NewError(configForwardLimitSwitchSource(allConfigs.forwardLimitSwitchSource, allConfigs.forwardLimitSwitchNormal, allConfigs.forwardLimitSwitchDeviceID, timeoutMs));
        errorCollection.NewError(configReverseLimitSwitchSource(allConfigs.reverseLimitSwitchSource, allConfigs.reverseLimitSwitchNormal, allConfigs.reverseLimitSwitchDeviceID, timeoutMs));
        

        //--------PIDs---------------//

        errorCollection.NewError(configurePID(allConfigs.primaryPID, 0, timeoutMs));
        errorCollection.NewError(configurePID(allConfigs.auxilaryPID, 1, timeoutMs));
        errorCollection.NewError(configSensorTerm(SensorTerm.Sum0, allConfigs.sum_0, timeoutMs));
        errorCollection.NewError(configSensorTerm(SensorTerm.Sum1, allConfigs.sum_1, timeoutMs));
        errorCollection.NewError(configSensorTerm(SensorTerm.Diff0, allConfigs.diff_0, timeoutMs));
        errorCollection.NewError(configSensorTerm(SensorTerm.Diff1, allConfigs.diff_1, timeoutMs));

        return errorCollection._worstError;

	}
    /**
     * Configures all peristant settings (overloaded so timeoutMs is 50 ms).
     *
	 * @param allConfigs        Object with all of the persistant settings
     *
     * @return Error Code generated by function. 0 indicates no error. 
     */
	public ErrorCode configAllSettings(VictorSPXConfiguration allConfigs) {
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
    public void getAllConfigs(VictorSPXConfiguration allConfigs, int timeoutMs) {
    
        baseGetAllConfigs(allConfigs, timeoutMs);
    
        getPIDConfigs(allConfigs.primaryPID, 0, timeoutMs);
        getPIDConfigs(allConfigs.auxilaryPID, 1, timeoutMs);
        allConfigs.sum_0 =  RemoteFeedbackDevice.valueOf(configGetParameter(ParamEnum.eSensorTerm, 0, timeoutMs));
        allConfigs.sum_1 =  RemoteFeedbackDevice.valueOf(configGetParameter(ParamEnum.eSensorTerm, 1, timeoutMs));
        allConfigs.diff_0 = RemoteFeedbackDevice.valueOf(configGetParameter(ParamEnum.eSensorTerm, 2, timeoutMs));
        allConfigs.diff_1 = RemoteFeedbackDevice.valueOf(configGetParameter(ParamEnum.eSensorTerm, 3, timeoutMs));
    
        allConfigs.forwardLimitSwitchSource = RemoteLimitSwitchSource.valueOf(configGetParameter(ParamEnum.eLimitSwitchSource, 0, timeoutMs));
        allConfigs.reverseLimitSwitchSource = RemoteLimitSwitchSource.valueOf(configGetParameter(ParamEnum.eLimitSwitchSource, 1, timeoutMs));
        allConfigs.forwardLimitSwitchDeviceID = (int) configGetParameter(ParamEnum.eLimitSwitchRemoteDevID, 0, timeoutMs);
        allConfigs.reverseLimitSwitchDeviceID = (int) configGetParameter(ParamEnum.eLimitSwitchRemoteDevID, 1, timeoutMs);
        allConfigs.forwardLimitSwitchNormal = LimitSwitchNormal.valueOf(configGetParameter(ParamEnum.eLimitSwitchNormClosedAndDis, 0, timeoutMs));
        allConfigs.reverseLimitSwitchNormal = LimitSwitchNormal.valueOf(configGetParameter(ParamEnum.eLimitSwitchNormClosedAndDis, 1, timeoutMs));
    
    }
    /**
     * Gets all persistant settings (overloaded so timeoutMs is 50 ms).
     *
	 * @param allConfigs        Object with all of the persistant settings
     */
    public void getAllConfigs(VictorSPXConfiguration allConfigs) {
        int timeoutMs = 0;
        getAllConfigs(allConfigs, timeoutMs); 
    }


}
