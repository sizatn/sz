package com.sizatn.sz.codegenerate;

import java.util.ArrayList;
import java.util.List;

import com.sizatn.sz.codegenerate.generate.impl.CodeGenerateOneToMany;
import com.sizatn.sz.codegenerate.generate.pojo.onetomany.MainTableVo;
import com.sizatn.sz.codegenerate.generate.pojo.onetomany.SubTableVo;

public class OneToManyCodeGenerator {
	public static void main(String[] args) {
		MainTableVo mainTable = new MainTableVo();
		mainTable.setTableName("product_order_main");
		mainTable.setEntityName("Order");
		mainTable.setEntityPackage("test2");
		mainTable.setFtlDescription("订单");
		mainTable.setPrimaryKeyPolicy("uuid");

		List<SubTableVo> subTables = new ArrayList<>();

		SubTableVo po = new SubTableVo();
		po.setTableName("product_order_customer");
		po.setEntityName("OrderCustomer");
		po.setEntityPackage("test2");
		po.setFtlDescription("客户明细");

		po.setForeignKeys(new String[] { "order_id" });
		subTables.add(po);

		SubTableVo po2 = new SubTableVo();
		po2.setTableName("product_order_ticket");
		po2.setEntityName("OrderTicket");
		po2.setEntityPackage("test2");
		po2.setFtlDescription("产品明细");

		po2.setForeignKeys(new String[] { "order_id" });
		subTables.add(po2);
		mainTable.setSubTables(subTables);

		try {
			(new CodeGenerateOneToMany(mainTable, subTables)).generateCodeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}