package frc.robot.subSystems.camera;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class VisionCamera {
	
	public static VisionCamera instance;
	
	public static VisionCamera getInstance()
    {
		if (instance == null)
		   instance = new VisionCamera();
	
		return instance;
    }
	
	private UsbCamera camera;
	
	private VisionThread visionThread;
	
	public VisionCamera(){
		camera = CameraServer.getInstance().startAutomaticCapture("cam1",0);
		camera.setResolution(640,480);
		
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline->{
			ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();
			//short hand for loop only dose one contour
			if(contours.size() != 0){
				MatOfPoint biggest = new MatOfPoint();
				for(MatOfPoint contour : contours){
					if(contour.width() * contour.height() > biggest.width() * biggest.height()){
						biggest = contour;
					}
				}
				Rect boundingBox = Imgproc.boundingRect(biggest);
				// ~ center for exact do calc with moments
				double centerX = (boundingBox.x) + (boundingBox.width/2);
				double centerY = (boundingBox.y) + (boundingBox.height/2);

				SmartDashboard.putNumber("Target Count", contours.size());
				SmartDashboard.putNumber("Main Target Center X", centerX);
				SmartDashboard.putNumber("Main Target Center Y", centerY);
			}
		});
		
		visionThread.start();
	}
}
