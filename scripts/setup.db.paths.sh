# move to starting dir
cd /temp

echo 'cleaning and setting up db directories'

# remove old data directories and the subdirs
rm -rf pg-data
mkdir pg-data
rm -rf pg-data-init
mkdir pg-data-init
cp example_postgres_db.session.sql pg-data-init/init.sql


# echo output
echo 'list directory'
ls -al *-data

# build complete env file
rm -f .env
envs=`ls *.env`
echo "" > .env
for file in $envs
do
    echo "# $file" >> .env
    cat $file >> .env
    echo "" >> .env
done

# cleanup and reset script_all.txt from all script_*.txt files
# drop last line and replace with Y except last file
rm -f src/script_all.txt
scripts=`ls src/script_*.txt`
echo "" > src/script_all.txt
for file in $scripts
do
    sed '$d' $file >> src/script_all.txt
    echo "Y" >> src/script_all.txt
done

# make last line exit
sed '$d' src/script_all.txt > src/script_all.tmp.txt
echo "N" >> src/script_all.tmp.txt
mv src/script_all.txt src/script_all.txt.join
sed '1d' src/script_all.tmp.txt | sed '/^$/d' > src/script_all.txt
