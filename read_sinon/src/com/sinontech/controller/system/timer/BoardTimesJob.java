package com.sinontech.controller.system.timer;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sinontech.service.read.article.ArticleService;
import com.sinontech.service.read.board.BoardService;
import com.sinontech.service.read.boarddetail.BoardDetailService;
import com.sinontech.util.DateUtil;
import com.sinontech.util.Logger;
import com.sinontech.util.PageData;

public class BoardTimesJob extends QuartzJobBean{
	protected static final Logger logger = Logger.getLogger(SignTimesJob.class);
	private ArticleService articleService;
	private BoardService boardService;
	private BoardDetailService boarddetailService;
	
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	public BoardService getBoardService() {
		return boardService;
	}
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	public BoardDetailService getBoarddetailService() {
		return boarddetailService;
	}
	public void setBoarddetailService(BoardDetailService boarddetailService) {
		this.boarddetailService = boarddetailService;
	}
	/**
	 * 
	 * @purpose：定时更新每个榜单的数据（每天更新一次）
	 * @return void
	 * @author liuzhen
	 * @Time：3018年4月18日 下午4:33:39
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		PageData pd = new PageData();
		try {
			//所有榜单
			List<PageData> list = this.boardService.listAll(pd);
			if(list.size()>0&&list!=null){
				pd.put("SORT_RULE",0);
				//清空系统匹配的榜单详情数据
				this.boarddetailService.delIsNotRuleOne(pd);
				for (int i = 0; i < list.size(); i++) {
					if("热销榜".equals(list.get(i).getString("BOARD_NAME"))){
						pd.put("BOARD_ID",1);
						for (int j = 0; j < 3; j++) {
							pd.put("BOARD_TYPE",j);
							//按排序规则查询除了榜单详情里已有的书籍	if("热销榜".equals(list.get(i).g外的书籍
							List<PageData> listArticle = this.articleService.seachBoardSortArticle(pd);
							if(listArticle.size()>0&&listArticle!=null){
								//查询榜单详情里已有的手动添加和手动排序的书籍
								List<PageData> boardDetailList = this.boarddetailService.seachIdType(pd);
								//判断榜单详情里有没有手动添加或者手动修改的书籍
								if(boardDetailList.size()>0&&boardDetailList!=null){
									for (int k = 0; k < boardDetailList.size(); k++) {
										pd.put("SORT", k+1);
										int count = 0;
										if(Integer.parseInt(boardDetailList.get(k).get("SORT").toString())>60){
											if(count == 0){
												for (int l = 0; l < listArticle.size(); l++) {
													pd.put("SORT", k+1+l);
													pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
													pd.put("CREATE_TIME", DateUtil.getTime());
													this.boarddetailService.save(pd);
												}
												count = Integer.parseInt(pd.get("SORT").toString());
											}
											count++;
											pd.put("SORT", count);
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}else{
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}
										//榜单里没有置底的数据
										if(count==0&&k==(boardDetailList.size()-1)){
											k+=2;
											for (int l = 0; l < listArticle.size(); l++) {
												pd.put("SORT", k+l);
												pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
												pd.put("CREATE_TIME", DateUtil.getTime());
												this.boarddetailService.save(pd);
											}
										}
									}
								}else{
									for (int l = 0; l < listArticle.size(); l++) {
										pd.put("SORT", l+1);
										pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
										pd.put("CREATE_TIME", DateUtil.getTime());
										this.boarddetailService.save(pd);
									}
								}
							}
						}
					}else if("收藏榜".equals(list.get(i).getString("BOARD_NAME"))){
						pd.put("BOARD_ID",2);
						for (int j = 0; j < 3; j++) {
							pd.put("BOARD_TYPE",j);
							//按排序规则查询除了榜单详情里已有的书籍	if("热销榜".equals(list.get(i).g外的书籍
							List<PageData> listArticle = this.articleService.seachBoardSortArticle(pd);
							if(listArticle.size()>0&&listArticle!=null){
								//查询榜单详情里已有的手动添加和手动排序的书籍
								List<PageData> boardDetailList = this.boarddetailService.seachIdType(pd);
								//判断榜单详情里有没有手动添加或者手动修改的书籍
								if(boardDetailList.size()>0&&boardDetailList!=null){
									for (int k = 0; k < boardDetailList.size(); k++) {
										pd.put("SORT", k+1);
										int count = 0;
										if(Integer.parseInt(boardDetailList.get(k).get("SORT").toString())>60){
											if(count == 0){
												for (int l = 0; l < listArticle.size(); l++) {
													pd.put("SORT", k+1+l);
													pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
													pd.put("CREATE_TIME", DateUtil.getTime());
													this.boarddetailService.save(pd);
												}
												count = Integer.parseInt(pd.get("SORT").toString());
											}
											count++;
											pd.put("SORT", count);
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}else{
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}
										//榜单里没有置底的数据
										if(count==0&&k==(boardDetailList.size()-1)){
											k+=2;
											for (int l = 0; l < listArticle.size(); l++) {
												pd.put("SORT", k+l);
												pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
												pd.put("CREATE_TIME", DateUtil.getTime());
												this.boarddetailService.save(pd);
											}
										}
									}
								}else{
									for (int l = 0; l < listArticle.size(); l++) {
										pd.put("SORT", l+1);
										pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
										pd.put("CREATE_TIME", DateUtil.getTime());
										this.boarddetailService.save(pd);
									}
								}
							}
						}
					}else if("人气榜".equals(list.get(i).getString("BOARD_NAME"))){
						pd.put("BOARD_ID",3);
						for (int j = 0; j < 3; j++) {
							pd.put("BOARD_TYPE",j);
							//按排序规则查询除了榜单详情里已有的书籍	if("热销榜".equals(list.get(i).g外的书籍
							List<PageData> listArticle = this.articleService.seachBoardSortArticle(pd);
							if(listArticle.size()>0&&listArticle!=null){
								//查询榜单详情里已有的手动添加和手动排序的书籍
								List<PageData> boardDetailList = this.boarddetailService.seachIdType(pd);
								//判断榜单详情里有没有手动添加或者手动修改的书籍
								if(boardDetailList.size()>0&&boardDetailList!=null){
									for (int k = 0; k < boardDetailList.size(); k++) {
										pd.put("SORT", k+1);
										int count = 0;
										if(Integer.parseInt(boardDetailList.get(k).get("SORT").toString())>60){
											if(count == 0){
												for (int l = 0; l < listArticle.size(); l++) {
													pd.put("SORT", k+1+l);
													pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
													pd.put("CREATE_TIME", DateUtil.getTime());
													this.boarddetailService.save(pd);
												}
												count = Integer.parseInt(pd.get("SORT").toString());
											}
											count++;
											pd.put("SORT", count);
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}else{
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}
										//榜单里没有置底的数据
										if(count==0&&k==(boardDetailList.size()-1)){
											k+=2;
											for (int l = 0; l < listArticle.size(); l++) {
												pd.put("SORT", k+l);
												pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
												pd.put("CREATE_TIME", DateUtil.getTime());
												this.boarddetailService.save(pd);
											}
										}
									}
								}else{
									for (int l = 0; l < listArticle.size(); l++) {
										pd.put("SORT", l+1);
										pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
										pd.put("CREATE_TIME", DateUtil.getTime());
										this.boarddetailService.save(pd);
									}
								}
							}
						}
					}else if("主编推荐榜".equals(list.get(i).getString("BOARD_NAME"))){
						pd.put("BOARD_ID",4);
						/*for (int j = 0; j < 3; j++) {
							pd.put("BOARD_TYPE",j);
							//按排序规则查询除了榜单详情里已有的书籍	if("热销榜".equals(list.get(i).g外的书籍
							List<PageData> listArticle = this.articleService.seachBoardSortArticle(pd);
							if(listArticle.size()>0&&listArticle!=null){
								//查询榜单详情里已有的手动添加和手动排序的书籍
								List<PageData> boardDetailList = this.boarddetailService.seachIdType(pd);
								//判断榜单详情里有没有手动添加或者手动修改的书籍
								if(boardDetailList.size()>0&&boardDetailList!=null){
									for (int k = 0; k < boardDetailList.size(); k++) {
										pd.put("SORT", k+1);
										int count = 0;
										if(Integer.parseInt(boardDetailList.get(k).get("SORT").toString())>60){
											if(count == 0){
												for (int l = 0; l < listArticle.size(); l++) {
													pd.put("SORT", k+1+l);
													pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
													pd.put("CREATE_TIME", DateUtil.getTime());
													this.boarddetailService.save(pd);
												}
												count = Integer.parseInt(pd.get("SORT").toString());
											}
											count++;
											pd.put("SORT", count);
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}else{
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}
										//榜单里没有置底的数据
										if(count==0&&k==(boardDetailList.size()-1)){
											k+=2;
											for (int l = 0; l < listArticle.size(); l++) {
												pd.put("SORT", k+l);
												pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
												pd.put("CREATE_TIME", DateUtil.getTime());
												this.boarddetailService.save(pd);
											}
										}
									}
								}else{
									for (int l = 0; l < listArticle.size(); l++) {
										pd.put("SORT", l+1);
										pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
										pd.put("CREATE_TIME", DateUtil.getTime());
										this.boarddetailService.save(pd);
									}
								}
							}
						}*/
					}else if("完结榜".equals(list.get(i).getString("BOARD_NAME"))){
						pd.put("BOARD_ID",5);
						for (int j = 0; j < 3; j++) {
							pd.put("BOARD_TYPE",j);
							//按排序规则查询除了榜单详情里已有的书籍	if("热销榜".equals(list.get(i).g外的书籍
							List<PageData> listArticle = this.articleService.seachBoardSortArticle(pd);
							if(listArticle.size()>0&&listArticle!=null){
								//查询榜单详情里已有的手动添加和手动排序的书籍
								List<PageData> boardDetailList = this.boarddetailService.seachIdType(pd);
								//判断榜单详情里有没有手动添加或者手动修改的书籍
								if(boardDetailList.size()>0&&boardDetailList!=null){
									for (int k = 0; k < boardDetailList.size(); k++) {
										pd.put("SORT", k+1);
										int count = 0;
										if(Integer.parseInt(boardDetailList.get(k).get("SORT").toString())>60){
											if(count == 0){
												for (int l = 0; l < listArticle.size(); l++) {
													pd.put("SORT", k+1+l);
													pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
													pd.put("CREATE_TIME", DateUtil.getTime());
													this.boarddetailService.save(pd);
												}
												count = Integer.parseInt(pd.get("SORT").toString());
											}
											count++;
											pd.put("SORT", count);
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}else{
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}
										//榜单里没有置底的数据
										if(count==0&&k==(boardDetailList.size()-1)){
											k+=2;
											for (int l = 0; l < listArticle.size(); l++) {
												pd.put("SORT", k+l);
												pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
												pd.put("CREATE_TIME", DateUtil.getTime());
												this.boarddetailService.save(pd);
											}
										}
									}
								}else{
									for (int l = 0; l < listArticle.size(); l++) {
										pd.put("SORT", l+1);
										pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
										pd.put("CREATE_TIME", DateUtil.getTime());
										this.boarddetailService.save(pd);
									}
								}
							}
						}
					}else if("连载榜".equals(list.get(i).getString("BOARD_NAME"))){
						pd.put("BOARD_ID",6);
						for (int j = 0; j < 3; j++) {
							pd.put("BOARD_TYPE",j);
							//按排序规则查询除了榜单详情里已有的书籍	if("热销榜".equals(list.get(i).g外的书籍
							List<PageData> listArticle = this.articleService.seachBoardSortArticle(pd);
							if(listArticle.size()>0&&listArticle!=null){
								//查询榜单详情里已有的手动添加和手动排序的书籍
								List<PageData> boardDetailList = this.boarddetailService.seachIdType(pd);
								//判断榜单详情里有没有手动添加或者手动修改的书籍
								if(boardDetailList.size()>0&&boardDetailList!=null){
									for (int k = 0; k < boardDetailList.size(); k++) {
										pd.put("SORT", k+1);
										int count = 0;
										if(Integer.parseInt(boardDetailList.get(k).get("SORT").toString())>60){
											if(count == 0){
												for (int l = 0; l < listArticle.size(); l++) {
													pd.put("SORT", k+1+l);
													pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
													pd.put("CREATE_TIME", DateUtil.getTime());
													this.boarddetailService.save(pd);
												}
												count = Integer.parseInt(pd.get("SORT").toString());
											}
											count++;
											pd.put("SORT", count);
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}else{
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}
										//榜单里没有置底的数据
										if(count==0&&k==(boardDetailList.size()-1)){
											k+=2;
											for (int l = 0; l < listArticle.size(); l++) {
												pd.put("SORT", k+l);
												pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
												pd.put("CREATE_TIME", DateUtil.getTime());
												this.boarddetailService.save(pd);
											}
										}
									}
								}else{
									for (int l = 0; l < listArticle.size(); l++) {
										pd.put("SORT", l+1);
										pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
										pd.put("CREATE_TIME", DateUtil.getTime());
										this.boarddetailService.save(pd);
									}
								}
							}
						}
					}else if("免费榜".equals(list.get(i).getString("BOARD_NAME"))){
						pd.put("BOARD_ID",7);
						for (int j = 0; j < 3; j++) {
							pd.put("BOARD_TYPE",j);
							//按排序规则查询除了榜单详情里已有的书籍	if("热销榜".equals(list.get(i).g外的书籍
							List<PageData> listArticle = this.articleService.seachBoardSortArticle(pd);
							if(listArticle.size()>0&&listArticle!=null){
								//查询榜单详情里已有的手动添加和手动排序的书籍
								List<PageData> boardDetailList = this.boarddetailService.seachIdType(pd);
								//判断榜单详情里有没有手动添加或者手动修改的书籍
								if(boardDetailList.size()>0&&boardDetailList!=null){
									for (int k = 0; k < boardDetailList.size(); k++) {
										pd.put("SORT", k+1);
										int count = 0;
										if(Integer.parseInt(boardDetailList.get(k).get("SORT").toString())>60){
											if(count == 0){
												for (int l = 0; l < listArticle.size(); l++) {
													pd.put("SORT", k+1+l);
													pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
													pd.put("CREATE_TIME", DateUtil.getTime());
													this.boarddetailService.save(pd);
												}
												count = Integer.parseInt(pd.get("SORT").toString());
											}
											count++;
											pd.put("SORT", count);
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}else{
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}
										//榜单里没有置底的数据
										if(count==0&&k==(boardDetailList.size()-1)){
											k+=2;
											for (int l = 0; l < listArticle.size(); l++) {
												pd.put("SORT", k+l);
												pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
												pd.put("CREATE_TIME", DateUtil.getTime());
												this.boarddetailService.save(pd);
											}
										}
									}
								}else{
									for (int l = 0; l < listArticle.size(); l++) {
										pd.put("SORT", l+1);
										pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
										pd.put("CREATE_TIME", DateUtil.getTime());
										this.boarddetailService.save(pd);
									}
								}
							}
						}
					}else if("VIP榜".equals(list.get(i).getString("BOARD_NAME"))){
						pd.put("BOARD_ID",8);
						for (int j = 0; j < 3; j++) {
							pd.put("BOARD_TYPE",j);
							//按排序规则查询除了榜单详情里已有的书籍	if("热销榜".equals(list.get(i).g外的书籍
							List<PageData> listArticle = this.articleService.seachBoardSortArticle(pd);
							if(listArticle.size()>0&&listArticle!=null){
								//查询榜单详情里已有的手动添加和手动排序的书籍
								List<PageData> boardDetailList = this.boarddetailService.seachIdType(pd);
								//判断榜单详情里有没有手动添加或者手动修改的书籍
								if(boardDetailList.size()>0&&boardDetailList!=null){
									for (int k = 0; k < boardDetailList.size(); k++) {
										pd.put("SORT", k+1);
										int count = 0;
										if(Integer.parseInt(boardDetailList.get(k).get("SORT").toString())>60){
											if(count == 0){
												for (int l = 0; l < listArticle.size(); l++) {
													pd.put("SORT", k+1+l);
													pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
													pd.put("CREATE_TIME", DateUtil.getTime());
													this.boarddetailService.save(pd);
												}
												count = Integer.parseInt(pd.get("SORT").toString());
											}
											count++;
											pd.put("SORT", count);
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}else{
											pd.put("BOARD_DETAIL_ID", boardDetailList.get(k).get("BOARD_DETAIL_ID"));
											this.boarddetailService.edit(pd);
										}
										//榜单里没有置底的数据
										if(count==0&&k==(boardDetailList.size()-1)){
											k+=2;
											for (int l = 0; l < listArticle.size(); l++) {
												pd.put("SORT", k+l);
												pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
												pd.put("CREATE_TIME", DateUtil.getTime());
												this.boarddetailService.save(pd);
											}
										}
									}
								}else{
									for (int l = 0; l < listArticle.size(); l++) {
										pd.put("SORT", l+1);
										pd.put("ARTICLE_ID", listArticle.get(l).get("ARTICLE_ID"));
										pd.put("CREATE_TIME", DateUtil.getTime());
										this.boarddetailService.save(pd);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
}
