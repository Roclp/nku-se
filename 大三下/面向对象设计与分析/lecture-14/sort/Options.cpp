#include "stdafx.h"
#include "string.h"
#include "stdio.h"
#include "Options.h"

Options Options::_instance;

Options::Options ( )
{
	_ignore_case     = false;
	_key_is_numeric  = false;
	_reverse_output  = false;
	_field_offset    = 0;
	_column_offset   = 0;
	_pivot_strat     = FIRST;
}

Options * Options::instance( ) 
{
	return & _instance;
}

void Options::parse_args (int argc, char **argv)
{
	int error_flag = 0;
	while ( (--argc>1) && ((*++argv)[0] == '-')) {
		switch ((*argv)[1]){
		  case 'i': _ignore_case    = true; break;
		  case 'n': _key_is_numeric = true; break;
	      case 'r': _reverse_output = true; break;
		  case 'f': sscanf(*++argv, "%d", & _field_offset);  --argc; break;
		  case 'c': sscanf(*++argv, "%d", & _column_offset); --argc; break;
		  case 'p': 
			   char pivot_name[80];
               sscanf(*++argv, "%s", & pivot_name); -- argc;
			   if (strcmp( pivot_name, "first") == 0 )
				   _pivot_strat = FIRST;
			   else if (strcmp( pivot_name, "random") == 0 )
				   _pivot_strat = RANDOM;
			   else  if (strcmp( pivot_name, "median3") == 0 )
				   _pivot_strat = MEDIAN3;
			   else
				   error_flag = 1;
			   break;
		  default: error_flag = 1; break;
		}
	}
	_file_name = *++argv;
}

bool Options::ignore_case( )
{
	return _ignore_case;
}

bool Options::key_is_numeric( )
{
	return _key_is_numeric;
}

bool Options::reverse_output( )
{
	return _reverse_output;
}

int  Options::field_offset( )
{
	return _field_offset;
}

int  Options::column_offset( )
{
	return _column_offset;
}

Options::Pivot_Strategy Options::pivot_strat( )
{
	return _pivot_strat;
}

char * Options::file_name ()
{
	return _file_name;
}
