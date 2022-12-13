package com.common.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.common.dao.FolderDAO;
import com.tring.domain.FolderVO;



@Service
public class FolderServiceImpl implements FolderService{
	
	@Inject
	private FolderDAO dao;
	
	@Override
	public List<FolderVO> userFolderList(FolderVO vo) throws Exception{
		return dao.userFolderList(vo);
	}
	@Override
	public int userFolderInsert(FolderVO vo) throws Exception{
		return dao.userFolderInsert(vo);
	}
	@Override
	public int userFolderNameUpdate(FolderVO vo) throws Exception{
		return dao.userFolderNameUpdate(vo);
	}
	@Override
	public int userFolderMove(FolderVO vo) throws Exception{
		return dao.userFolderMove(vo);
	}
	@Override
	public int userFolderMove_level(String folder_uid, int folder_level) throws Exception{
		return dao.userFolderMove_level(folder_uid, folder_level);
	}
	@Override
	public int userFolderDelete(String uid) throws Exception{
		return dao.userFolderDelete(uid);
	}
	@Override
	public List<String> userFolderDown_uid(String uid) throws Exception{
		return dao.userFolderDown_uid(uid);
	}
	
	@Override
	public List<FolderVO> userFolderDataList(String uid) throws Exception{
		return dao.userFolderDataList(uid);
	}
	@Override
	public List<String> userFolderDataUid_check(String uid) throws Exception{
		return dao.userFolderDataUid_check(uid);
	}
	@Override
	public int userFolderDataInsert(FolderVO vo) throws Exception{
		return dao.userFolderDataInsert(vo);
	}
	@Override
	public int userFolderDataMove(FolderVO vo) throws Exception{
		return dao.userFolderDataMove(vo);
	}
	@Override
	public int userFolderDataDelete(String uid) throws Exception{
		return dao.userFolderDataDelete(uid);
	}

}
