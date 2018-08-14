/**
 *
 * Copyright (c) 2014, the Railo Company Ltd. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library.  If not, see <http://www.gnu.org/licenses/>.
 * 
 **/
/**
 * Implements the CFML Function arrayavg
 */
package lucee.runtime.functions.list;

import lucee.runtime.PageContext;
import lucee.runtime.exp.FunctionException;
import lucee.runtime.exp.PageException;
import lucee.runtime.ext.function.BIF;
import lucee.runtime.functions.closure.Every;
import lucee.runtime.op.Caster;
import lucee.runtime.type.UDF;
import lucee.runtime.type.util.StringListData;


public final class ListEvery extends BIF {

	private static final long serialVersionUID = -7873096972268260607L;

	public static boolean call(PageContext pc , String list, UDF udf,String delimiter, boolean includeEmptyFields, boolean multiCharacterDelimiter, boolean parallel, double maxThreads) throws PageException {
		return _call(pc, list, udf,delimiter,includeEmptyFields,multiCharacterDelimiter, parallel, (int)maxThreads);
	}
	private static boolean _call(PageContext pc , String list, UDF udf,String delimiter, boolean includeEmptyFields, boolean multiCharacterDelimiter, boolean parallel, int maxThreads) throws PageException {
		StringListData data=new StringListData(list,delimiter,includeEmptyFields,multiCharacterDelimiter);
		
		return Every.call(pc, data, udf, parallel, maxThreads);
	}

	@Override
	public Object invoke(PageContext pc, Object[] args) throws PageException {

		if(args.length==2)
			return _call(pc, Caster.toString(args[0]), Caster.toFunction(args[1]),",",false,true, false, 20);
		if(args.length==3)
			return _call(pc, Caster.toString(args[0]), Caster.toFunction(args[1]),Caster.toString(args[2]),false,true, false, 20);
		if(args.length==4)
			return _call(pc, Caster.toString(args[0]), Caster.toFunction(args[1]),Caster.toString(args[2]),Caster.toBooleanValue(args[3]),true, false, 20);
		if(args.length==5)
			return _call(pc, Caster.toString(args[0]), Caster.toFunction(args[1]),Caster.toString(args[2]),Caster.toBooleanValue(args[3]), Caster.toBooleanValue(args[4]),false, 20);
		if(args.length==6)
			return _call(pc, Caster.toString(args[0]), Caster.toFunction(args[1]),Caster.toString(args[2]),Caster.toBooleanValue(args[3]), Caster.toBooleanValue(args[4]), Caster.toBooleanValue(args[5]),20);
		if(args.length==7)
			return call(pc, Caster.toString(args[0]), Caster.toFunction(args[1]),Caster.toString(args[2]),Caster.toBooleanValue(args[3]), Caster.toBooleanValue(args[4]), Caster.toBooleanValue(args[5]), Caster.toDoubleValue(args[6]));
		
		throw new FunctionException(pc, "ListEvery", 2, 7, args.length);
	}
}