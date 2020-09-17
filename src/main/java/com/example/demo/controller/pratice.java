//package com.example.demo.controller;
//
//import org.springframework.boot.SpringBootVersion;
//import org.springframework.core.SpringVersion;
//
//public class pratice {
//    /*public static void main(String[] args) {
//        System.out.println(SpringVersion.getVersion());
//        System.out.println(SpringBootVersion.getVersion());
//    }*/
//    //提交卡信息
//    class SubmitTask extends AsyncTask<String, Object, ResultModel<JieyiAsyncCard>> {
//        private boolean hasCardFlag = false;
//        private String cardNo;
//        private String name;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            showLoadingDialog();
//        }
//
//        @Override
//        protected ResultModel<JieyiAsyncCard> doInBackground(String... params) {
//            HashMap<String,Object> para = new HashMap<>();
//            para.put("identifyNo", AppApplication.user.identifyNo);
//            //查询此人名下   t_medical_card （医疗卡）下所有卡
//            ResultModel<ArrayList<JieyiCard>> cards = HttpApiJieyi.jieyiParserArray(JieyiCard.class, "person/listCard", para);
//            if(cards!=null)
//            {
//                if(cards.statue == Statue.SUCCESS)
//                {
//                    if(cards.list!=null&&cards.list.size()>0)
//                    {
//                        for(JieyiCard card:cards.list)
//                        {
//                            if(card.cardNo.equals(params[1]))
//                            {
//                                hasCardFlag = true;
//                                break;
//                            }
//                        }
//
//                    }
//
//                    if(!hasCardFlag)
//                    {
//                        //异步绑卡
//                        HashMap<String,Object> map = new HashMap<String,Object>();
//                        map.put("identifyNo",AppApplication.user.identifyNo);
//                        map.put("cardNo",params[1]);
//                        map.put("cardType",cardTypeResult.index);
//                        map.put("otherIdentifyNo",params[0]);
//                        map.put("patientName",params[2]);
//                        cardNo = params[1];
//                        name = params[2];
//                        ResultModel<JieyiAsyncCard> result = HttpApiJieyi.jieyiRequest(JieyiAsyncCard.class,"async/card/bind",map);
//                        //循环查询异步绑卡状态
//                        for(int i=0;i<13;i++)
//                        {
//                            HashMap<String,Object> checkPara = new HashMap<>();
//                            List<String> uuids = new ArrayList<>();
//                            uuids.add(result.data.uuid);
//                            checkPara.put("uuids",uuids);
//                            ResultModel<List<JieyiAsyncCard>> checkResult =   HttpApiJieyi.jieyiParserArray(JieyiAsyncCard.class, "async/card/query",checkPara);
//                            if(checkResult.statue == Statue.SUCCESS&&checkResult.list!=null&&checkResult.list.size()>0)
//                            {
//                                JieyiAsyncCard asyncCard = checkResult.list.get(0);
//                                if(asyncCard.status==1||asyncCard.status==11)
//                                {
//                                    //绑卡成功或失败，返回结果
//                                    ResultModel<JieyiAsyncCard> checkMode = new ResultModel<>();
//                                    checkMode.data = asyncCard;
//                                    checkMode.message = checkResult.message;
//                                    checkMode.statue = Statue.SUCCESS;
//                                    return  checkMode;
//                                }
//                            }
//                            try
//                            {
//                                Thread.sleep(2000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                        return result;
//                    }
//
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(final ResultModel<JieyiAsyncCard> result) {
//            closeLoadingDialog();
//            boolean showFlag = false;
//            if(hasCardFlag)
//            {
//                showFlag = true;
//                //请求成功，但无实体卡
//                showDialog("提示", "您已绑定该卡，可返回在卡列表查看！"
//                        , "确定", "",null, null);
//            }
//            else if (null != result)
//            {
//                if (result.statue == Statue.SUCCESS)
//                {
//                    showFlag = true;
//                    if(result.data.status==1)
//                    {
//                        //绑定成功
//                        Toast.makeText(baseContext, "保存卡成功", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent();
//                        intent.putExtra("from","real");
//                        intent.putExtra("data",cardNo);
//                        setResult(RESULT_OK,intent);
//                        back();
//                    }
//                    else if(result.data.status==0)
//                    {
//                        //等待
//                        showDialog("提示", "正在努力为您查询信息中，请稍后再进入卡管理列表查看是否绑定成功！"
//                                , "确定", "", new OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        List<JieyiAsyncCard> pauseCards = LocalDataUtil.getInstance().getPauseCards();
//                                        if(pauseCards==null)
//                                        {
//                                            pauseCards = new ArrayList<>();
//                                        }
//                                        JieyiAsyncCard card = new JieyiAsyncCard();
//                                        card.cardNo = cardNo;
//                                        card.patientName = name;
//                                        card.uuid = result.data.uuid;
//                                        pauseCards.add(card);
//                                        LocalDataUtil.getInstance().setPauseCards(pauseCards);
//                                        Intent intent = new Intent();
//                                        intent.putExtra("data",cardNo);
//                                        setResult(RESULT_OK,intent);
//                                        back();
//                                    }
//                                }, null);
//                    }
////					else if(result.data.status==11)
////					{
////						//失败
////						showDialog("提示", "绑卡失败，请检查信息输入是否正确;"
////										+ "该卡需要在医院有就诊记录，否则请前往医院自助机完成自助绑卡；"
////										+ "或者返回新建闵行捷医电子就诊卡，在就诊前，到医院自助机完成卡替换。"
////								, "确定", "",null, null);
////					}
//                    else
//                    {
//                        //其他
//                        if(!TextUtils.isEmpty(result.data.msg))
//                        {
//                            showDialog("提示", result.data.msg
//                                    , "确定", "",null, null);
//                        }
//                        else
//                        {
//                            showDialog("提示", "绑卡失败，请检查信息输入是否正确;"
//                                            + "该卡需要在医院有就诊记录，否则请前往医院自助机完成自助绑卡；"
//                                            + "或者返回新建闵行捷医电子就诊卡，在就诊前，到医院自助机完成卡替换。"
//                                    , "确定", "",null, null);
//                        }
//                    }
//                }
//                else if(result.statue ==Statue.ERROR)
//                {
//                    showFlag = true;
//                    //请求成功，但无实体卡
//                    showDialog("提示", "绑卡失败，请检查信息输入是否正确;"
//                                    + "该卡需要在医院有就诊记录，否则请前往医院自助机完成自助绑卡；"
//                                    + "或者返回新建闵行捷医电子就诊卡，在就诊前，到医院自助机完成卡替换。"
//                            , "确定", "",null, null);
//                    if(!TextUtils.isEmpty(result.message))
//                    {
//                        showToast(result.message);
//                    }
//                }
//                else
//                {
//                    if(!TextUtils.isEmpty(result.message))
//                    {
//                        showFlag = true;
//                        showToast(result.message);
//                    }
//                }
//            }
//
//            if(!showFlag)
//            {
//                showToast("请求失败，请重试！");
//            }
//        }
//    }
//}
