package com.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tring.domain.FolderVO;

@Repository
public class FolderDAOImpl implements FolderDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.folder.mappers";

	@Override
	public List<FolderVO> userFolderList(FolderVO vo) throws Exception {
		return sql.selectList(namespace + ".userFolderList", vo);
	}
	@Override
	public int userFolderInsert(FolderVO vo) throws Exception{
		return sql.insert(namespace + ".userFolderInsert", vo);
	}
	@Override
	public int userFolderNameUpdate(FolderVO vo) throws Exception{
		return sql.update(namespace + ".userFolderNameUpdate", vo);
	}
	@Override
	public int userFolderMove(FolderVO vo) throws Exception{
		return sql.update(namespace + ".userFolderMove", vo);
	}
	@Override
	public int userFolderMove_level(String folder_uid, int folder_level) throws Exception{
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("folder_uid", folder_uid);
		map.put("folder_level", folder_level);
		return sql.update(namespace + ".userFolderMove_level", map);
	}
	@Override
	public int userFolderDelete(String uid) throws Exception{
		return sql.update(namespace + ".userFolderDelete", uid);
	}
	@Override
	public List<String> userFolderDown_uid(String uid) throws Exception{
		return sql.selectList(namespace + ".userFolderDown_uid", uid);
	}
	
	@Override
	public List<FolderVO> userFolderDataList(String uid) throws Exception{
		return sql.selectList(namespace + ".userFolderDataList", uid);
	}
	@Override
	public List<String> userFolderDataUid_check(String uid) throws Exception{
		return sql.selectList(namespace + ".userFolderDataUid_check", uid);
	}
	@Override
	public int userFolderDataInsert(FolderVO vo) throws Exception{
		return sql.insert(namespace + ".userFolderDataInsert", vo);
	}
	@Override
	public int userFolderDataMove(FolderVO vo) throws Exception{
		return sql.update(namespace + ".userFolderDataMove", vo);
	}
	@Override
	public int userFolderDataDelete(String uid) throws Exception{
		return sql.delete(namespace + ".userFolderDataDelete", uid);
	}
}
