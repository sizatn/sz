package com.sizatn.sz.codegenerate.window;

import java.util.ArrayList;

import com.sizatn.sz.codegenerate.generate.impl.CodeGenerateOneToMany;
import com.sizatn.sz.codegenerate.generate.pojo.onetomany.MainTableVo;
import com.sizatn.sz.codegenerate.generate.pojo.onetomany.SubTableVo;

public class OneToMainUtil {
	public static void main(String[] args) {
		MainTableVo localMainTableVo = new MainTableVo();
		localMainTableVo.setTableName("product_order_main");
		localMainTableVo.setEntityName("TestOrderMain");
		localMainTableVo.setEntityPackage("test2");
		localMainTableVo.setFtlDescription("订单");

		ArrayList<SubTableVo> localArrayList = new ArrayList<SubTableVo>();

		SubTableVo localSubTableVo1 = new SubTableVo();
		localSubTableVo1.setTableName("product_order_customer");
		localSubTableVo1.setEntityName("TestOrderCustom");
		localSubTableVo1.setEntityPackage("test2");
		localSubTableVo1.setFtlDescription("客户明细");

		localSubTableVo1.setForeignKeys(new String[] { "order_id" });
		localArrayList.add(localSubTableVo1);

		SubTableVo localSubTableVo2 = new SubTableVo();
		localSubTableVo2.setTableName("product_order_ticket");
		localSubTableVo2.setEntityName("TestOrderTicket");
		localSubTableVo2.setEntityPackage("test2");
		localSubTableVo2.setFtlDescription("产品明细");

		localSubTableVo2.setForeignKeys(new String[] { "order_id" });
		localArrayList.add(localSubTableVo2);
		localMainTableVo.setSubTables(localArrayList);

		try {
			(new CodeGenerateOneToMany(localMainTableVo, localArrayList)).generateCodeFile();
		} catch (Exception localException) {

			localException.printStackTrace();
		}
	}
}
