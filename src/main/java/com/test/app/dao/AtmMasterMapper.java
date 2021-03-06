package com.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.app.config.MyBatisUtil;
import com.test.app.dto.AtmMaster;
import com.test.app.dto.ViewAtmDto;
import com.test.app.entity.AtmCurrencyTab;
import com.test.app.entity.CtlaTab;
import com.test.app.entity.CtrTab;
import com.test.app.entity.DefTab;
import com.test.app.entity.RtTab;
import com.test.app.entity.TcpTab;

@Repository
public class AtmMasterMapper implements IAtmMasterMapper {

	@Override
	public int storeNewAtmInfo(AtmMaster atmMaster) {
		// TODO Auto-generated method stub
		int nora = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			nora = nora + session.insert("insert_rttab", atmMaster.getRttab());
			nora = nora + session.insert("insert_tcptab", atmMaster.getTcptab());
			nora = nora + session.insert("insert_deftab", atmMaster.getDeftab());
			nora = nora + session.insert("insert_ctlatab", atmMaster.getCtlatab());
			nora = nora + session.insert("insert_ctrtab", atmMaster.getCtrtab());
			nora = nora + session.insert("insert_ctimtab", atmMaster.getCtimtablist());
			nora = nora + session.insert("insert_atmcurrtab", atmMaster.getAtmcurrtablist());
			nora = nora + session.insert("insert_ecfoptab", atmMaster.getEcfoptablist());
			nora = nora + session.insert("insert_devattrtab", atmMaster.getDevattr());
			nora = nora + session.insert("insert_devkeytab", atmMaster.getDevkeytab());
			nora = nora + session.insert("insert_tmkcomptab", atmMaster.getTmkcomptab());
			nora = nora + session.insert("insert_tmkreqtab", atmMaster.getTmkreqtab());
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return nora;
	}

	@Override
	public List<ViewAtmDto> listAllActiveAtm() {
		// TODO Auto-generated method stub
		List<ViewAtmDto> atmlist = new ArrayList<ViewAtmDto>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			atmlist = session.selectList("listAllActiveAtm");
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return atmlist;
	}

	@Override
	public AtmMaster getAtmMasterByPid(String pid) {
		// TODO Auto-generated method stub
		AtmMaster atmlist = new AtmMaster();
		AtmMaster temp = new AtmMaster();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {			
			RtTab rttab = session.selectOne("selRtByPid", pid);
			temp.setRttab(rttab);
			TcpTab tcptab = session.selectOne("selTcpByPid", pid);
			temp.setTcptab(tcptab);
			DefTab deftab = session.selectOne("selDefByPid", pid);
			temp.setDeftab(deftab);
			CtlaTab ctlatab = session.selectOne("selCtlaByPid", pid);
			temp.setCtlatab(ctlatab);
			CtrTab ctrtab = session.selectOne("selCtrByPid", pid);
			temp.setCtrtab(ctrtab);
			List<AtmCurrencyTab> atmcurrlist = session.selectList("selAtmCurrByPid", pid);
			temp.setAtmcurrtablist(atmcurrlist);
			session.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			atmlist = temp;
			session.close();
		}
		return atmlist;
	}

}
