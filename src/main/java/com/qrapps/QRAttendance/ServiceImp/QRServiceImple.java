package com.qrapps.QRAttendance.ServiceImp;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qrapps.QRAttendance.BaseModel.QRStore;
import com.qrapps.QRAttendance.DAO.QR_DAO;
import com.qrapps.QRAttendance.Utils.QRUtils;
import com.qrapps.QRAttendance.services.CommonService;

@Component
public class QRServiceImple implements CommonService {

	@Autowired
	private QRUtils qrUtile;
	@Autowired
	private QR_DAO qrRepo;
	@Override
	public byte[] addEntity(Object object) {
		// TODO Auto-generated method stub
		try {
			
			QRStore data=(QRStore)object;
			com.qrapps.QRAttendance.Entities.QRStore mapObje = mappingObject(data);
			com.qrapps.QRAttendance.Entities.QRStore qrSave = qrRepo.save(mapObje);
			class QrModel {
					public int courseId;
					public String courseName;
					public Date date;
					public String qrId;
					@Override
					public String toString() {
						return "{'courseId' :" + courseId + ", 'courseName':" + courseName + ", 'date':" + date + ", 'qrId':"+qrId+" }";
					}
					
					
			}
			QrModel qrCode =new QrModel();
			qrCode.courseId=mapObje.getCourseId();
			qrCode.courseName=mapObje.getCourseName();
			qrCode.date=mapObje.getCreatedOn();
			qrCode.qrId=""+qrSave.getId();
		return qrUtile.generateQRCodeImage(qrCode.toString());
		
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Object deleteEntity(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object viewEntityById(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateEntity(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	private com.qrapps.QRAttendance.Entities.QRStore mappingObject(QRStore req) {
		com.qrapps.QRAttendance.Entities.QRStore obj= new com.qrapps.QRAttendance.Entities.QRStore();
		obj.setActive(true);
		obj.setCourseId( Integer.parseInt( req.getCourseId()));
		obj.setCreatedOn(req.getCreatedOn());
		obj.setOtherData(req.getOtherData());
		obj.setCourseName(req.getCourseName());
		return obj;
	}

	@Override
	public Object viewAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object viewById(Object object) {
		// TODO Auto-generated method stub
		return qrRepo.findById(null);
	}

}
