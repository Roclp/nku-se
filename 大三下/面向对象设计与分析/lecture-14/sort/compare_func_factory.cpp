#include "stdafx.h"
#include <string.h>
#include "stdlib.h"
#include "compare_func_factory.h"

Compare_Func_Factory Compare_Func_Factory::_instance;

Compare_Func_Factory::Compare_Func_Factory ( )
{
	_func_pointer = strcmp;
}

Compare_Func_Factory * Compare_Func_Factory::instance( ) 
{
	return & _instance;
}

static int numcmp (const char *s1, const char * s2) 
{
	double d1 = strtod (s1, 0), d2 = strtod (s2, 0);

	if (d1 < d2) 
		return -1;
	else if (d1 > d2) 
		return 1;
	else 
		return 0;
}

static int strcmp_nocase (const char *p1, const char * p2) 
{
	char *s1 =new char [ strlen(p1)+1 ] ;
	char *s2 =new char [ strlen(p2)+1 ] ;
	strcpy(s1, p1);  strcpy(s2, p2);    
	_strlwr(s1);   	_strlwr(s2);
	int result = strcmp(s1, s2);
	delete s1;
	delete s2;
	return result;
}

void Compare_Func_Factory::set_func_pointer()
{
	if ( Options::instance()->key_is_numeric() )
		_func_pointer = numcmp;
	else if ( Options::instance()->ignore_case() )
		_func_pointer = strcmp_nocase;
	else
		_func_pointer = strcmp;
}

Compare_Func_Factory::Func_Pointer Compare_Func_Factory::get_func_pointer()
{
    return _func_pointer;
};
