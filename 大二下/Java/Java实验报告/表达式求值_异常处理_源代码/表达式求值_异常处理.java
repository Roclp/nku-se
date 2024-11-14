import java.util.*;
import java.math.*;
public class Main {
	
	class errorExpression extends Exception{
		errorExpression(){
			super();
		}
	}
	
	class varUndefined extends Exception{
		varUndefined(){
			super();
		}
	}
	 
	class varUnassigned extends Exception{
		varUnassigned(){
			super();
		}
	}
	
	public int judgeVarUnassigned(String []str,int length,String expression,String []type ,char []var,double []value) throws varUnassigned{
		int type_len=0;
		int var_len=0;
		int val_len=0;
		for(int i=0;i<length;i++) {
			value[i]=3.14999;
		}
		for(int i=0;i<length;i++) {
			String temp=new String();
			temp=str[i];
			int temp_len=temp.length();
			boolean flag=false;//有空格则是定义变量（以及赋值）
			int j;//记录空格的位置
			for( j=0;j<temp_len;j++) {
				if(temp.charAt(j)==' ') {
					flag=true;
					break;
				}
			}
			if(flag) {//类型部分
				String temp_type=new String();
				for(int k=0;k<j;k++) {
					temp_type=temp_type+temp.charAt(k);
				}
				type[type_len++]=temp_type;
			}
			if(flag) {//变量部分
				int k=j;
				var[var_len++]=temp.charAt(++k);
			}
			if(flag) {//赋值部分
				int k=j;
				boolean flag1=false;
				for(;k<temp_len;k++) {
					if(temp.charAt(k)=='=') {
						flag1=true;
						break;
					}
				}
				if(flag1) {//后面是值而不是分号
					k+=1;//值的位置或者负号位置
					boolean flag2=true;
					if(temp.charAt(k)=='-') {
						k+=1;
						flag2=false;
					}
					if(type[type_len-1].equals("int")) {
						double temp_val=3.14999;
						int count=0;
						for(int kk=k;kk<temp_len&&temp.charAt(kk)!=';';kk++) {
							if(count==0) {
								temp_val=0;
								count++;
							}
							if(temp.charAt(kk)>='0'&&temp.charAt(kk)<='9') {
								temp_val=temp_val*10+(double)(temp.charAt(kk)-'0');
							}
						}
						if(temp_val!=3.14999) {
							if(flag) {
								value[var_len-1]=temp_val;
							}
							else {
								value[var_len-1]=temp_val*(-1);
							}
							continue;
						}
					}
					else {//小数
						double int_val=3.14999;
						double float_val=3.14999;
						double count=0;//小数位数
						int count1=0;
						int kk;
						for( kk=k;temp.charAt(kk)>='0'&&temp.charAt(kk)<='9';kk++) {//整数部分
							if(count1==0) {
								int_val=0;
								count1++;
							}
							int_val=int_val*10+(double)(temp.charAt(kk)-'0');
						}
						count1=0;
						if(kk<temp_len&&temp.charAt(kk)!=';') {
							for(kk=kk+1;kk<temp_len&&temp.charAt(kk)!=';';kk++) {//小数部分
								if(count1==0) {
									float_val=0;
									count1++;
								}
								float_val=float_val*10+(int)(temp.charAt(kk)-'0');
								count++;
							}
						}
						float_val=float_val/Math.pow(10, count);
						if(int_val!=3.14999&&float_val!=3.14999) { 
							if(flag) {
								value[var_len-1]=int_val+float_val;
							}
							else {
								value[var_len-1]=(-1)*(int_val+float_val);
							}
							continue;
						}
						if(int_val==3.14999&&float_val!=3.14999) {
							if(flag2) {
								value[var_len-1]=float_val;
							}
							else {
								value[var_len-1]=float_val*(-1);
							}
							continue;
						}
						if(int_val!=3.14999&&float_val==3.14999) {
							if(flag2) {
								value[var_len-1]=int_val;
							}
							else {
								value[var_len-1]=-1*int_val;
							}
							continue;
						}
					}
				}
				continue;
			}
			
			if(flag==false) {//定义时没有赋值，进行赋值的语句
				char temp_var=temp.charAt(0);
				int position;//变量的下标
				for(position=0;position<var_len;position++) {
					if(var[position]==temp_var) {
						break;
					}
				}
				if(type[position].equals("int")) {//变量类型是整型
					double temp_val=3.14999;
					int count=0;
					int kk=2;//值的位置
					for(;kk<temp_len&&temp.charAt(kk)>='0'&&temp.charAt(kk)<='9';kk++) {
						if(count==0) {
							temp_val=0;
							count++;
						}
						temp_val=temp_val*10+(double)(temp.charAt(kk)-'0');
					}
					if(temp_val!=3.14999) {
						value[position]=temp_val;
					}
				}
				if(type[position].equals("float")) {
					int kk=2;
					double int_val=3.14999;
					double float_val=3.14999;
					int count=0;
					int count1=0;
					for(;temp.charAt(kk)!='.'&&temp.charAt(kk)!=';'&&kk<temp_len;kk++) {
						if(count1==0) {
							int_val=0;
							count1++;
						}
						int_val=int_val*10+(double)(temp.charAt(kk)-'0');
					}
					count1=0;
					for(kk=kk+1;kk<temp_len&&temp.charAt(kk)!=';';kk++) {
						if(count1==0) {
							float_val=0;
							count1=0;
						}
						float_val=float_val*10+(double)(temp.charAt(kk)-'0');
						count++;
					}
					float_val=float_val/(Math.pow(10, count));
					if(int_val!=3.14999&&float_val!=3.14999) { 
						value[position]=int_val+float_val;
						continue;
					}
					if(int_val==3.14999&&float_val!=3.14999) {
						value[position]=float_val;
						continue;
					}
					if(int_val!=3.14999&&float_val==3.14999) {
						value[position]=int_val;
						continue;
					}
					val_len++;
					//i_f[position]=false;
				}
			}	
		}//for
		int exp_len=expression.length();
		for(int i=0;i<exp_len;i++) {
			char temp_char=expression.charAt(i);
			for(int j=0;j<var_len;j++) {
				if(temp_char==var[j]) {
					if(value[j]==3.14999) {
						varUnassigned e=new varUnassigned();
						throw e;
					}
					else {
						continue;
					}
				}
				else {
					continue;
				}
			}
		}
		return var_len;
	}
	
	public void judgeVarUndefined(String []str,int length,String expression) throws varUndefined{//[]str记录输入的变量以及赋值
		char []var=new char[length];//记录输入的变量
		int var_len=0;
		for(int i=0;i<length;i++) {//提取输入的变量
			String temp=new String();
			temp=str[i];//记录一行输入
			int temp_length=temp.length();
			boolean flag=false;
			for(int j=0;j<temp_length;j++) {
				if(temp.charAt(j)==' ') {
					flag=true;
					continue;
				}
				if(flag) {
					char tempchar;//临时记录变量
					tempchar=temp.charAt(j);
					var[var_len++]=tempchar;
					flag=false;
					break;
				}
			}
			
		}
		int exp_len=expression.length();
		for(int i=0;i<exp_len;i++) {
			char temp=expression.charAt(i);
			if(temp=='('||temp==')'||temp=='+'||temp=='-'||temp=='*'||temp=='/'||temp=='%'||temp=='='||temp=='?'||(temp>='0'&&temp<='9')||temp=='.') {
				continue;
			}
			else {//找到了一个变量
				int j;
				for( j=0;j<var_len;j++) {
					if(var[j]==temp) {
						break;
					}
				}
				if(j==var_len) {//表达式中的变量没有出现
					varUndefined e=new varUndefined();
					throw e;
				}
				else {
					continue;
				}
			}
		}
	}
	
	public void judgeExpression(String expresstion) throws errorExpression {//判断表达式是否正确
		Stack<Character> stack=new Stack<>(); 
		int length=expresstion.length();
		for(int i=0;i<length;i++) {
			if(expresstion.charAt(i)=='(') {
				stack.push(expresstion.charAt(i));
			}
			if(expresstion.charAt(i)==')') {
				stack.pop();
			}
			
		}
		if(!stack.empty()) {
			errorExpression e=new errorExpression();
			throw e;
		}
	}
	
	public int wai(char a) {
		if(a=='+') {return 1;}
		else {
			if(a=='-') {return 1;}
			else {
				if(a=='*') {return 2;}
				else {
					if(a=='/') {return 2;}
					else {
						if(a=='(') {return 3;}
						else {
							if(a=='%') {return 4;}
							else {
								return 0;
							}
						}
					}
				}
			}
		}
	}
	
	public int nei(char a) {
		if(a=='+') {return 1;}
		else {
			if(a=='-') {return 1;}
			else {
				if(a=='*') {return 2;}
				else {
					if(a=='/') {return 2;}
					else {
						if(a=='(') {return 0;}
						else {
							if(a=='%') {return 4;}
							else {
								return 0;
							}
						}
					}
				}
			}
		}
	}
	
	public void cal(String []shizi,int shizi_len,char []var,int var_len,double []value,String []type) {
		Stack<Double> ans=new Stack<>();
		double []val=new double[shizi_len];
		String []val_type=new String[shizi_len];
		Stack<String> stack=new Stack<>();//记录压进栈的值得类型
		int val_len=0;
		for(int i=0;i<shizi_len;i++) {
			if(shizi[i].charAt(0)>='a'&&shizi[i].charAt(0)<='z') {
				for(int j=0;j<var_len;j++) {
					if(var[j]==shizi[i].charAt(0)) {
						val[val_len]=value[j];
						val_type[val_len]=type[j];
						val_len++;
						break;
					}
				}
			}
			else {
				val_len++;
			}
		}
		for(int i=0;i<shizi_len;i++) {
			
		if(shizi[i].charAt(0)>='0'&&shizi[i].charAt(0)<='9') {
			String str=new String();
			str=shizi[i];
			int len=shizi[i].length();
			double num=0;
			double int_num=0;
			int j;
			for( j=0;j<len&&str.charAt(j)!='.';j++) {
				int_num=int_num*10+(int)(str.charAt(j)-'0');
			}
			double float_num=3.14999;
			int count=0;
			if(j+1<len) {
				float_num=0;
				for(j=j+1;j<len;j++) {
					float_num=float_num*10+(int)(str.charAt(j)-'0');
					count++;
				}
			}
			float_num=float_num/Math.pow(10, count);
			num=int_num+float_num;
			if(float_num==3.14999) {
				num=int_num;
				ans.push(num);
				stack.push("int");
			}
			else {
				num=int_num+float_num;
				ans.push(num);
				stack.push("float");
			}
			continue;
		}
			
		if(shizi[i].charAt(0)>='a'&&shizi[i].charAt(0)<='z') {
			ans.push(val[i]);
			stack.push(val_type[i]);
		}
		else {
			String type1=new String();
			String type2=new String();
			double num2=ans.peek();
			type2=stack.peek();
			ans.pop();
			stack.pop();
			double num1=ans.peek();
			type1=stack.peek();
			ans.pop();
			stack.pop();
			switch(shizi[i].charAt(0)) {
			case '+':
				if((type1.equals("float")&&type2.equals("float"))||(type1.equals("int")&&type2.equals("float"))||(type1.equals("float")&&type2.equals("int"))) {
					ans.push(num1+num2);
					stack.push("float");
				}
				else {
					ans.push((num1+num2)-((num1+num2)%1));
					stack.push("int");
				}
				 break;
			case '-':
				if((type1.equals("float")&&type2.equals("float"))||(type1.equals("int")&&type2.equals("float"))||(type1.equals("float")&&type2.equals("int"))) {
					ans.push(num1-num2);
					stack.push("float");
				}
				else {
					ans.push((num1-num2)-((num1-num2)%1));
					stack.push("int");
				}
				break;
			case '*':
				if((type1.equals("float")&&type2.equals("float"))||(type1.equals("int")&&type2.equals("float"))||(type1.equals("float")&&type2.equals("int"))) {
					ans.push(num1*num2);
					stack.push("float");
				}
				else {
					ans.push((num1*num2)-((num1*num2)%1));
					stack.push("int");
				}
				 break;
			case '/':
				if((type1.equals("float")&&type2.equals("float"))||(type1.equals("int")&&type2.equals("float"))||(type1.equals("float")&&type2.equals("int"))) {
					ans.push(num1/num2);
					stack.push("float");
				}
				else {
					ans.push((num1/num2)-((num1/num2)%1));
					stack.push("int");
				}
				break;
			case '%':
				if((type1.equals("float")&&type2.equals("float"))||(type1.equals("int")&&type2.equals("float"))||(type1.equals("float")&&type2.equals("int"))) {
					ans.push(num1%num2);
					stack.push("float");
				}
				else {
					ans.push((num1%num2)-((num1%num2)%1));
					stack.push("int");
				}
				break;
			}
		}
		}
		if(stack.peek().equals("int")) {
			System.out.println(String.format("%.0f", ans.peek()));
		}
		else {
			System.out.println(String.format("%.2f", ans.peek()));
		}
	}
	
	public void caculate(String exp,String []type,char []var, int var_len,double []value) {//计算表达式的值
		int exp_len=exp.length();
		String []shizi=new String[exp_len];//存储后缀表达式
		Stack<Character> stack = new Stack<>();//存储运算符
		int shizi_len=0;
		for(int i=0;exp.charAt(i)!='=';i++) {
			if((exp.charAt(i)>='0'&&exp.charAt(i)<='9')) {//表达式中 
				String temp=new String();
				int k;
				for( k=i;(exp.charAt(k)>='0'&&exp.charAt(k)<='9')||exp.charAt(k)=='.';k++) {
					temp=temp+exp.charAt(k);
				}
				i=k-1;
				shizi[shizi_len++]=temp;
				continue;
			}
			if(exp.charAt(i)>='a'&&exp.charAt(i)<='z') {//读取变量
				String temp=new String();
				temp=temp+exp.charAt(i);
				shizi[shizi_len++]=temp;
			}
			else {//读取符号
				if(stack.isEmpty()) {//栈空，压栈
					stack.push(exp.charAt(i));
				}
				else {//栈非空
					if(exp.charAt(i)==')') {
						for(;stack.peek()!='(';) {
							String temp=new String();
							temp=temp+stack.peek();
							shizi[shizi_len++]=temp;
							stack.pop();
						}
						stack.pop();
						continue;
					}
					if(wai(exp.charAt(i))>nei(exp.charAt(i))) {
						stack.push(exp.charAt(i));
					}
					else {
						for(;(!stack.isEmpty())&&(wai(exp.charAt(i))<=nei(stack.peek()));) {
							String temp=new String();
							temp=temp+stack.peek();
							shizi[shizi_len++]=temp;
							stack.pop();
						}
						stack.push(exp.charAt(i));
					}
					
				}
			}
		}
		
		while(!stack.isEmpty()) {
			String temp=new String();
			temp=temp+stack.peek();
			shizi[shizi_len++]=temp;
			stack.pop();
		}
		boolean flag=true;//判断输入输入变量是否都是整型
		for(int i=0;exp.charAt(i)!='=';i++) {
			for(int j=0;j<var_len;j++) {
				if(var[j]==exp.charAt(i)) {
					if(type[j].equals("float")) {
						flag=false;
						break;
					}
				}
			}
		}
		cal(shizi, shizi_len,var, var_len,value,type);
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String []str=new String[999];
		String expression=new String();
		int length=0;
		while(true) {
			String temp=new String();
			temp=sc.nextLine();
			int temp_len=temp.length();
			if(temp.charAt(temp_len-1)!='?') {
				str[length++]=temp;
			}
			else {
				expression=temp;
				break;
			}
		}
		String []type = new String[length];//数据类型
		char []var=new char[length];//变量
		double []value=new double[length];//值
		int var_len=0;
		Main a=new Main();
		try {
			a.judgeExpression(expression);
			a.judgeVarUndefined(str,length,expression);
			var_len=a.judgeVarUnassigned(str, length, expression,type,var,value);
			a.caculate(expression,type,var, var_len,value);
		}
		catch(errorExpression e) {
			System.out.println("wrong - error expression");
		}
		catch(varUndefined e) {
			System.out.println("wrong - variable undefined");
		}
		catch(varUnassigned e) {
			System.out.println("wrong - variable unassigned");
		}
		sc.close();
	}
}