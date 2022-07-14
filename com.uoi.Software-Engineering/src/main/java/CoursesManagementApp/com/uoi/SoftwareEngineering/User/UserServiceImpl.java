package CoursesManagementApp.com.uoi.SoftwareEngineering.User;

/*
@Service @Transactional
public  class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    private User user;

    public User saveUser(User user){
        return userRepo.save(user);
    }

   @Override
    public User updateUser(String username) {
        User user=userRepo.findByUsername(username);
        return userRepo.save(user);

    }

    @Override
    public void deleteUser(String username ) {
        User user=userRepo.findByUsername(username);
        userRepo.delete(user);
    }


    public void changeRoleOfUser(String username,int role){
        user=getUser(username);
        user.setRole(role);
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUser(String username){
        return userRepo.findByUsername(username);
    }

    public boolean checkLogin(String username,String password){
        if (getUser(username).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public List<User> getAllInstructors(){
        List<User> users=new ArrayList<User>();
        for(User user :getAllUsers()){
            if(user.getRole()==0){
                users.add(user);
            }
        }
        return users;
    }

    public  void login(){}

}*/
