package com.sinontech.service.read.rechargeset;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("rechargesetService")
public class RechargeSetService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		pd.put("CREATE_TIME",DateUtil.getTime());
		if(!"".equals(pd.getString("STATE"))||null==pd.getString("STATE")){
			pd.put("STATE",0);
		}
		dao.save("RechargeSetMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("RechargeSetMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("RechargeSetMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("RechargeSetMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("RechargeSetMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("RechargeSetMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("RechargeSetMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 *排序+1 
	 */
	public void numberupone(PageData pd) throws Exception {
		dao.update("RechargeSetMapper.numberupone", pd);
	}
	
	/*
	 * 获取最后一个数据的排序号
	 */
	public PageData seachnext() throws Exception {
		PageData pd = new PageData();
		return (PageData)dao.findForObject("RechargeSetMapper.seachnext", pd);
	}
	/*
	 * 修改状态（显示/隐藏）
	 */
	public void update(PageData pd) throws Exception {
		dao.update("RechargeSetMapper.update", pd);
	}

	/*
	 * 序号加
	 */
	public void editNoPlus(PageData pd) throws Exception {
		dao.update("RechargeSetMapper.editNoPlus", pd);
	}
	/*
	 * 序号减
	 */
	public void editNoReduce(PageData pd) throws Exception {
		dao.update("RechargeSetMapper.editNoReduce", pd);
	}
	/*
	 * vip列表
	 */
	public List<PageData> listvip(Page page) throws Exception {
		return (List<PageData>)dao.findForList("RechargeSetMapper.viplistPage", page);
	}
	
}

