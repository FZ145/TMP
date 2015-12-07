<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="org.jfree.data.general.DefaultPieDataset"%>
<%@ page import="org.jfree.data.category.CategoryDataset" %>
<%@ page import="org.jfree.data.category.DefaultCategoryDataset" %>
<%@ page import="org.jfree.chart.JFreeChart"%>
<%@ page import="org.jfree.chart.plot.PiePlot"%>
<%@ page import="org.jfree.chart.ChartRenderingInfo"%>
<%@ page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@ page import="org.jfree.chart.urls.StandardPieURLGenerator"%>
<%@ page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@ page import="org.jfree.chart.encoders.SunPNGEncoderAdapter"%>
<%@ page import="org.jfree.chart.StandardChartTheme"%>
<%@ page import="org.jfree.chart.ChartFactory"%>
<%@ page import="org.jfree.chart.plot.CategoryPlot"%>
<%@ page import="org.jfree.chart.plot.PlotOrientation"%>
<%@ page import="org.jfree.chart.axis.CategoryAxis"%>
<%@ page import="org.jfree.chart.renderer.category.BarRenderer"%>
<%@ page import="org.jfree.chart.labels.StandardCategoryItemLabelGenerator"%>
<%@ page import="java.awt.Font"%>
<%@ page import="java.awt.Color"%>
<%@ page import="tmp.entity.AbstractReputation" %>
<HTML>
<HEAD>
    <TITLE>Welcome to Jfreechart !</TITLE>
</HEAD>
<BODY>
<div align="center">
    <form action="/query/queryReputation.do" >
        <label>
            <select name="entityType">
                <option value="1">组件</option>
                <option value="2">租户</option>
                <option value="3">云服务提供商</option>
            </select>
        </label>
        <input name="entityUid" type="text" placeholder="请输入声誉评估实体的UID"/>
        <input type="submit" value="查询"/><br/>
    </form>
</div>
<%--<%--%>
    <%--//实现饼状图--%>
    <%--DefaultPieDataset data = new DefaultPieDataset();--%>
    <%--data.setValue("六月", 500);--%>
    <%--data.setValue("七月", 580);--%>
    <%--data.setValue("八月", 828);--%>

    <%--PiePlot plot = new PiePlot(data);--%>
    <%--JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);--%>

    <%--chart.setBackgroundPaint(java.awt.Color.white);  //可选，设置图片背景色--%>
    <%--chart.setTitle("Welcome to Jfreechart !"); //可选，设置图片标题--%>

    <%--ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());--%>

    <%--//500是图片长度，300是图片高度--%>
    <%--String filename = ServletUtilities.saveChartAsPNG(chart, 800, 500, info, session);--%>
    <%--String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;--%>
<%--%>--%>
<%
    Object reputations = request.getAttribute("reputations");
    if (reputations !=null) {
        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
        //显示柱状图
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
        for (AbstractReputation reputation : reputations) {

        }
        mDataset.addValue(2000, "清华大学", "本科生");
        mDataset.addValue(1500, "清华大学", "研究生");
        mDataset.addValue(1000, "清华大学", "博士生");
        mDataset.addValue(900, "清华大学", "讲师");
        mDataset.addValue(800, "清华大学", "副教授");
        mDataset.addValue(300, "清华大学", "教授");
        mDataset.addValue(600, "清华大学", "行政人员");
        mDataset.addValue(400, "清华大学", "管理人员");

        //创建主题样式
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        //设置图表标题
        mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
        //设置轴向字体
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));
        //设置图例字体
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        //应用主题
        ChartFactory.setChartTheme(mChartTheme);

        JFreeChart mChart = ChartFactory.createBarChart3D(
                "学校人员分布图",
                "类型",
                "数量",
                mDataset,
                PlotOrientation.VERTICAL,
                true,
                true,true);
        //设置内部属性
        CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
        CategoryAxis mDomainAxis = mPlot.getDomainAxis();
        //设置柱状图距离x轴最左端（即y轴）的距离百分比10%
        //mDomainAxis.setLowerMargin(0.1);
        mDomainAxis.setUpperMargin(0.1);
        //柱体显示数值
        BarRenderer mRenderer = new BarRenderer();
        mRenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        mRenderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 15));
        mRenderer.setItemLabelsVisible(true);
        mPlot.setRenderer(mRenderer);

        //500是图片长度，300是图片高度
        String filenamebar = ServletUtilities.saveChartAsPNG(mChart, 800, 500, info, session);
        String graphURLbar = request.getContextPath() + "/servlet/DisplayChart?filename=" + filenamebar;


%>

<%--<%--%>
    <%--//实现折现图--%>
    <%--DefaultCategoryDataset mDatasetline = new DefaultCategoryDataset();--%>
    <%--mDatasetline.addValue(1, "First", "2013");--%>
    <%--mDatasetline.addValue(3, "First", "2014");--%>
    <%--mDatasetline.addValue(2, "First", "2015");--%>
    <%--mDatasetline.addValue(6, "First", "2016");--%>
    <%--mDatasetline.addValue(5, "First", "2017");--%>
    <%--mDatasetline.addValue(12, "First", "2018");--%>
    <%--mDatasetline.addValue(14, "Second", "2013");--%>
    <%--mDatasetline.addValue(13, "Second", "2014");--%>
    <%--mDatasetline.addValue(12, "Second", "2015");--%>
    <%--mDatasetline.addValue(9, "Second", "2016");--%>
    <%--mDatasetline.addValue(5, "Second", "2017");--%>
    <%--mDatasetline.addValue(7, "Second", "2018");--%>

    <%--StandardChartTheme mChartThemeline = new StandardChartTheme("CN");--%>
    <%--mChartThemeline.setLargeFont(new Font("黑体", Font.BOLD, 20));--%>
    <%--mChartThemeline.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));--%>
    <%--mChartThemeline.setRegularFont(new Font("宋体", Font.PLAIN, 15));--%>
    <%--ChartFactory.setChartTheme(mChartThemeline);--%>

    <%--JFreeChart mChartline = ChartFactory.createLineChart(--%>
            <%--"折线图",--%>
            <%--"年份",--%>
            <%--"数量",--%>
            <%--mDatasetline,--%>
            <%--PlotOrientation.VERTICAL,--%>
            <%--true,--%>
            <%--true,--%>
            <%--false);--%>

    <%--CategoryPlot mPlotline = (CategoryPlot)mChart.getPlot();--%>
    <%--mPlotline.setBackgroundPaint(Color.LIGHT_GRAY);--%>
    <%--mPlotline.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线--%>
    <%--mPlotline.setOutlinePaint(Color.RED);//边界线--%>

    <%--//500是图片长度，300是图片高度--%>
    <%--String filenameline = ServletUtilities.saveChartAsPNG(mChartline, 800, 500, info, session);--%>
    <%--String graphURLline = request.getContextPath() + "/servlet/DisplayChart?filename=" + filenameline;--%>
<%--%>--%>



<P ALIGN="CENTER">
    <img src="<%= graphURLbar %>" width=500 height=300 border=0 usemap="#<%= filenamebar %>">
    <%--<img src="<%= graphURL %>" width=500 height=300 border=0 usemap="#<%= filename %>">--%>
    <%--<img src="<%= graphURLline %>" width=500 height=300 border=0 usemap="#<%= filenameline %>">--%>
</P>
<%}%>
</BODY>
</HTML>