package com.common.service;

import java.util.List;

import com.tring.domain.FolderVO;

public interface FolderService {
	
	public List<FolderVO> userFolderList(FolderVO vo) throws Exception;
	public int userFolderInsert(FolderVO vo) throws Exception;
	public int userFolderNameUpdate(FolderVO vo) throws Exception;
	public int userFolderMove(FolderVO vo) throws Exception;
	public int userFolderMove_level(String folder_uid, int folder_level) throws Exception;
	public int userFolderDelete(String uid) throws Exception;
	public List<String> userFolderDown_uid(String uid) throws Exception;
	
	public List<FolderVO> userFolderDataList(String uid) throws Exception;
	public List<String> userFolderDataUid_check(String uid) throws Exception;
	public int userFolderDataInsert(FolderVO vo) throws Exception;
	public int userFolderDataMove(FolderVO vo) throws Exception;
	public int userFolderDataDelete(String uid) throws Exception;
}
