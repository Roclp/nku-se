#ifndef Options_H
#define Options_H

class Options
{
public:
	enum Pivot_Strategy { FIRST, RANDOM, MEDIAN3 };

    static Options * instance( );
    void parse_args (int argc, char **argv);     
    bool ignore_case( );
	bool key_is_numeric( );
	bool reverse_output( );
    int  field_offset  ( ); 
	int  column_offset ( );
    Pivot_Strategy pivot_strat();
    char * file_name ();
private:
    Options ( );
	bool _ignore_case,  _key_is_numeric, _reverse_output;
    int  _field_offset, _column_offset;
    Pivot_Strategy _pivot_strat;
    static Options  _instance;
	char * _file_name;
};

#endif