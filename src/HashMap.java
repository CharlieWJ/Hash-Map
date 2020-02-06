
public class HashMap <K,V> implements Map<K,V> {
//public class HashMap <K extends Comparable<K>, V> implements Map<K,V> {
    private HashMap[] map;
    private Entry<K,V>[] entryList;

    public HashMap(){
        this.entryList=new Entry[64];
    }
    public HashMap(int buckets){
        this.entryList=new Entry[buckets];
    }

    @Override
    public void clear() {
        int len=this.entryList.length;
        this.entryList=new Entry[len];
    }

    @Override
    public boolean containsKey(K key) {
        int idx=hash(key);
        if(this.entryList[idx]!=null){
            return this.entryList[idx].getKey().equals(key);
        }
        else if(this.entryList[idx]==null){
            return false;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        boolean bool=false;
        for(Entry<K, V> k : this.entryList) {
            if (k!=null) {
                if(k.getValue().equals(value)) {
                    bool=true;
                    break;
                } } }
        return bool;
    }

    @Override
    //Purpose: This method returns the value to which the specified key is mapped.
    // If the Map does not contain the specified key, the method instead returns null.
    public V get(K key) {
        Entry<K,V> find = new Entry(this.entryList[hash(key)].getKey(),this.entryList[hash(key)].getValue(),this.entryList[hash(key)].getNext());
        while (find!=null) {
            if(find.getKey().equals(key)) {
                return find.getValue();
            }
            find=find.getNext();
        }
        return null;
    }

    @Override
    //Comment: Make sure this works.
    public boolean isEmpty() {
        return size()==0;//If the list contains no elements then it returns true, otherwise it returns false.
    }

    @Override
    //Purpose: This method associates the specified key with the specified value in the Map
    //       and returns the newly associated value, if the specified key is not already associated with a non-null value.
    //       If the specified key is already associated with a non-null value,
    //       the association is left unchanged, and the current value is returned instead.
    public V put(K key, V value) {
        Entry<K, V> entry = new Entry<K,V>(key, value, null);
        int idx=hash(key);
        Entry<K, V> hold =this.entryList[idx];
        if (hold==null) {
            this.entryList[idx]=entry;
            return entry.getValue();
        }
        else {
            //Checks to see if the keys already exist
            while (hold.getNext()!=null) {
                if (hold.getKey().equals(key)) {
                    return hold.getValue();
                }
                hold=hold.getNext();
            }
            if (hold.getKey().equals(key)) {
                hold.setValue(value);
            } else {
                hold.setNext(entry);
            }
        }
        return hold.getValue();
    }


    @Override
    public V remove(K key) {
        V val=null;
        Entry<K,V>[] holder=this.entryList;
        int idx=hash(key);
        if(containsKey(key)) {
            if (this.entryList[idx].getKey().equals(key)) {
                val = this.entryList[idx].getValue();
                this.entryList[idx] = null;
            }
        }
        return val;
    }

    @Override
    //Purpose
    // If the specified key is already mapped to a value, this
    // method will return the previous value associated with the key and replace it with the input
    // value. If the specified key is not mapped to a value, this method will simply return null,
    // altering nothing in the HashMap.
    public V replace(K key, V value) {
        V holder=null;
        int idx=hash(key);
        if (containsKey(key)){
            holder=this.entryList[idx].getValue();
            this.entryList[idx].setValue(value);
            //System.out.println(holder);
            return holder;
        }
        return null;
    }

    @Override
    public int size() {
        int count=0;
        int bucketLen=0;
        Entry<K,V>[] holder=this.entryList;
        for(int i=0;i<this.entryList.length;i++) {
            if (this.entryList[i]!=null) {
                count++;
                while(this.entryList[i].getNext()!=null){
                    bucketLen++;
                    count++;
                    //this.entryList[i].getNext();
                    this.entryList[i]=this.entryList[i].getNext();
                }
            }
        }
        this.entryList=holder;
        return count;
    }

    public void display(){
        int count=0;
        if(isEmpty()){
            System.out.println("{}");
        }
        else if(size()==1){
            for(int i=0;i<this.entryList.length;i++){
                if(this.entryList[i]!=null){
                    System.out.println("{"+this.entryList[i].getKey()+":"+this.entryList[i].getValue()+"}");
                }
            }
        }
        else {
            System.out.print("{");
            for (Entry<K, V> kvEntry : this.entryList) {
                if (kvEntry != null) {
                    if(count==size()-1){
                        System.out.print(""+kvEntry.getKey() + ":" + kvEntry.getValue() + "");
                    }
                    else{
                    System.out.print(""+kvEntry.getKey() + ":" + kvEntry.getValue() + ", ");}
                    count++;
                }
            }
            System.out.print("}");
        }
    }

    private int hash(K key){
        int hash=0,prime=31;
        String str=(String) key;
        for(int i=0;i<str.length();i++){
            hash=(hash*prime+str.charAt(i))%this.entryList.length;
        }
        return hash;
    }



//Purpose: Below I made a main method to test my function before attempting to run it with the provided .txt files.
    /*
    public static void main(String[] args){

        HashMap map=new HashMap();
        System.out.println("isEmpty() "+map.isEmpty());
        //map.hash("the");map.hash("a");map.hash("he");map.hash("fg");
        map.put("king","king");
        map.put("the","the");map.put("ding","ding");map.put("there","there");map.put("that","that");
        System.out.println(".get() "+map.get("the"));
        System.out.println(".containsValue() "+map.containsValue("the"));
        System.out.println(".containsKey() "+map.containsKey("the"));
        //map.size();
        System.out.println("isEmpty() "+map.isEmpty());
        map.display();
        System.out.println("\n"+map.remove("the"));
        System.out.println();
        System.out.println(map.replace("that","kid"));
        System.out.println(map.replace("king","Kong"));
        System.out.println(map.replace("there","ItIs!"));
        map.display();
        map.clear();
        System.out.println();
        map.display();
    }
    */
}
