*环境：Spring Boot 2.5.6 + H2 + Lombok + Junit4 + Mockito*

# 一.提高单元测试覆盖率的意义与价值

​		在想如何提单元覆盖率之前，我们需要了解什么是单元测试覆盖率，单元测试覆盖率是一种**软件测试的度量指标**，指在所有功能代码中，完成了单元测试的代码所占的比例。

​		单元测试覆盖率 = 被测代码行数 / 参测代码总行数 * 100%  （**行覆盖率 / 语句覆盖**）

> **Note:**
>
> 1.一般情况下， 参测代码总行数是指**排除配置文件、以及测试代码本身的所有功能代码的总行数**。<font color="#660000">我们卡中心同时也不计算MyBatis的xml文件</font>
>
> 2.单元测试的度量方式有：**行覆盖率 / 语句覆盖**，**分支覆盖**，**条件覆盖**

然后，我们做单元测试是为了什么呢。

**1. 是想通过单元测试来保证代码质量？**

​			仔细一想就会发现，单元测试高它并不能保证代码的质量，也就是说两者没有什么联系。单元测试覆盖率完全是可以“造假”，例如在单元测试的时候写一些没有实际业务价值的测试用例，使覆盖率**“虚高”**。[软件开发教父，Martin Fowler](https://martinfowler.com/bliki/AssertionFreeTesting.html)

**2. 是想通过单元测试保证业务逻辑不会出错？**

​			一个业务功能的实现并不仅仅依赖于某一个方法、某一个类，那么通过单元测试能够保证的业务逻辑也是十分有限的，不可能做到**“不会出错”**。不然怎么还有集成测试，组件测试等。

**3.那么既然不能保证质量又不能保证业务逻辑不会出错，我们提高覆盖率还有什么意义与价值呢？**

1.如果底层测试不够充分就只能靠顶层测试来保证，而越往顶层所需要花费的成本也就越高。

2.**在你写的测试代码是值得信任的前提下**，测试覆盖率可以快速帮我们找到没有测试的代码，**让我们在重构、优化这些没有被测试的代码时更有底气**。

![测试金字塔示意图](https://qijian-1301807797.cos.ap-guangzhou.myqcloud.com/markdown/%E6%B5%8B%E8%AF%95%E9%87%91%E5%AD%97%E5%A1%94%E7%A4%BA%E6%84%8F%E5%9B%BE.png)

note:想要了解更多：[人人都值得学习的UI自动化](https://www.yuedun.wang/blogdetail/5e96f1d8bd7e796e7100a71a/)

> **单元测试：**最下层是单元测试，单元测试是自动化测试策略稳固的根基，因此也是金字塔结构的最底层。

~~说了这么多，单元测试覆盖率的意义，接下来就来搞清楚单元测试。~~

------

# 二.单元测试进行中

## 1. 什么是单元测试

​         定义：单元测试是开发者编写的一小段代码，**用于检验代码的一个很小的，很明确的功能是否正确**。通常而言，一个单元测试是用于判断某个特定条件下特定函数的行为（《单元测试之道 Java版》）。换句话说，指对软件中的**最小可测试单元进行检查和验证**，针对的是类的方法。

​		目的：执行单元测试，是为了证明某段代码的的行为确实和开发者所期待的一直。

## 2. 为什么要做单元测试

​		这个简单有效的技术就是为了令代码更加完美。当基层的代码不再可靠时，那么必须要的改动就无法只局限在底层（**底层代码修改高层代码也需要需改**）。于是，一个对底层代码的修改会导致几乎所有一连串的代码需要修改，修改就会越来越多，越来越复杂。~~由于地层代码的不可以甚至有可能导致项目的整体以失败而告终。~~

## 3. 单元测试中的FIRST原则

### 3.1 F(Fast):快速

​		在调试bug时，需要频繁去运行单元测试验证结果是否正确。如果单元测试足够快速，就可以省去不必要浪费的时间，提高工作效率。

> @SpringBootTest会启动整个项目，连接数据库和redis，就出现很慢的问题。

### 3.2 I(Isolated):隔离

​		好的单元测试是每个单元测试只关注逻辑的一个方面，每个单元测试之间不应该产生依赖，不会因为测试顺序的不同导致运行结果的不同。不要依赖和修改外部数据等其他共享的资源，做到测试前后的一致性。

> 使用junit编写单元测试时，不同层级之间存在依赖关系。例如：service依赖dao，dao依赖数据库。

### 3.3 R(Repeatable):可重复

​		单元测试需要保持运行稳定，每次运行都需要得到同样的结果，如果间歇性的失败，会导致我们不断的去查看这个测试，不可靠的测试也就失去了意义。

> 使用junit同样不能做到可重复，因为依赖数据库数据的原因，当数据变动后。单元测试的结果也就可能会不一样。

### 3.4 S(Self-verifying)：自我验证

​		单元测试需要采用Assert函数等进行自验证，即当单元测试执行完毕之后就可得知测试结果，全程无需人工接入。

> 使用junit单元测试，不能做到自我验证。

### 3.5 T(Timely)：及时

​		等代码稳定运行再来补齐单元测试可能是低效的，最有效的方式是在写好功能函数接口后（实现函数功能前）进行单元测试。



## 4. Java流行的mock框架选择

1. Mockito: 这是一个开源的 mock 框架，它提供了一种简单而强大的方法来实现 mock。它具有良好的文档和社区支持，是目前使用**最广泛的 Java mock 框架之一**。
2. PowerMock: 这是一个功能强大的 mock 框架，它可以**模拟静态方法、私有方法和构造函数**等。

注意：mock工具还有EasyMock` 、`WireMock`、`JMockit`、`Mock`、`Moco

> ​		Mockito是Java流行的一种Mock框架，使用Mock技术能让我们隔离外部依赖以便对我们自己的业务逻辑代码进行单元测试，在编写单元测试时，不需要再进行繁琐的初始化工作， 在需要调用某一个接口时，直接模拟一个假方法，并任意指定方法的返回值。		Mockito的工 作原理是**通过创建依赖对象的proxy，所有的调用先经过proxy对象，proxy对象拦截了所有的请求再根据预设的返回值进行处理。**

# 三.Mockito单元测试的正确姿势

## 1.前置概念

### 1.1 **被测对象**：

​		即我们想要测试的对象，比如userService、xxUtils等。

### 1.2 Mock 对象

​		一般为我们被测对象的**依赖对象**。典型如被测对象的成员变量。主要是一些测试中我们不关注的对象。我们只想要得到这些对象的方法的返回值。而不关注这些方法的具体执行逻辑。此时我们可以将这些对象创建为mock对象。

### 1.3 Stub（桩）

​		桩指的是用来替换具体功能的程序段。桩程序可以用来模拟已有程序的行为或是对未完成开发程序的一种临时替代。也就是对调用方法的模拟。

### 1.4 **spy对象**：

​		和mock对象一样，它可以作为被测对象的依赖对象。此时它和mock对象的最大的区别是mock对象的方法如果没有被存根，调用时会返回相应对象的空值；而spy对象的方法被调用时则会调用真实的代码逻辑。



## 2. 为什么要mock

​		在写单元测试的过程中，我们往往会遇到要测试的类有很多依赖，这些依赖的类/对象/资源又有别的依赖，从而形成一 个大的依赖树，要在单元测试的环境中完整地构建这样的依赖，是一件很困难的事情。

<img src="https://qijian-1301807797.cos.ap-guangzhou.myqcloud.com/markdown/%E4%BE%9D%E8%B5%96%E6%A0%91.png" alt="image-20230321162709643" style="zoom:80%;" />

​		使用mockito后  我们只需要Mock B类和C类（用虚拟对象来代替）

<img src="https://qijian-1301807797.cos.ap-guangzhou.myqcloud.com/markdown/mock%E4%BE%9D%E8%B5%96%E6%A0%91.png" alt="image-20230321162855191" style="zoom:80%;" />

> 这样就可以很好的遵循 单元测试中的FIRST原则中的FIRS



## 3.引入依赖

可直接引入Spring Boot的test，它已经帮我们解决了依赖问题，并且天然的支持Mockito和JUnit5（需要Spring Boot 2.4.0+，否则支持的是JUnit4）

```xml
<!--Mockito单元测试框架 -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>2.28.2</version>
        </dependency>
<!--         powermock依赖  -->
        <dependency>
            <groupId>org.powermock</groupId>
            <artifactId>powermock-module-junit4</artifactId>
            <version>2.0.4</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.powermock</groupId>
            <artifactId>powermock-api-mockito2</artifactId>
            <version>2.0.4</version>
            <scope>test</scope>
        </dependency>
```

## 4.一次完整的mock

一次完整的Mock，包括

- 设定目标  （Student result = studentMapper.getById(id);）
- 打桩并设定方法参数  （Mockito.when(studentMapper.getById(Mockito.anyInt()))）
- 预期返回结果   （.thenReturn(student)）
- 消费并检验返回结果  （Assertions.assertThat(studentService.getById(student.getId()).getClassNum()).isEqualTo(01);）

```java
	//service代码
    @Override
    public Student getById(Integer id) {
        Student result = studentMapper.getById(id);
        log.info("stu:{}",result);
        return result;
    }


//不用理会方法中调用的函数结果，mock打桩后可以指定结果返回
    @Test
    public void testGetById() {
        Student student = new Student();
        student.setId(001);
        student.setClassNum(01);
        //when里面带的是条件，thenReturn里面表示的是返回结果
        Mockito.when(studentMapper.getById(Mockito.anyInt()))
                .thenReturn(student);
        Assertions.assertThat(studentService.getById(student.getId()).getClassNum()).isEqualTo(01);
    }

```



测试service 的逻辑时，需要mock 掉dao层的数据返回

![image-20230319233232302](https://qijian-1301807797.cos.ap-guangzhou.myqcloud.com/markdown/service%E8%B0%83%E7%94%A8mapper%E6%95%B0%E6%8D%AE%E8%BF%94%E5%9B%9E.png)

### 3.1 项目目录结构

源码目录包结构：

![image-20230320165359277](https://qijian-1301807797.cos.ap-guangzhou.myqcloud.com/markdown/202303201654179.png)

测试目录包结构

![image-20230320164656552](https://qijian-1301807797.cos.ap-guangzhou.myqcloud.com/markdown/mockito-demo%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%841.png)



### 3.2 初始化

方式一：

通过 Mockito 类的静态方法 mock 来创建 Mock 对象.

```java
@Slf4j
public class UserServiceImplTest {

    private static final UserService testService = new UserServiceImpl();
    private static final UserMapper userMapper = Mockito.mock(UserMapper.class);

    //针对所有测试，只执行一次，且必须为static void  运行junit测试类时第一个被执行的方法
    //被用作执行计算代价很大的任务，如打开数据库连接。
    @BeforeClass
    public static void init() {
        ReflectionTestUtils.setField(testService, "userMapper", userMapper);
    }

    //针对所有测试，只执行一次，且必须为static void  运行junit测试类是最后一个被执行的方法
    //该类型的方法被用作执行类似关闭数据库连接的任务。
    @AfterClass
    public static void afterClass(){
    }
  
}
```



方式二：

​		是使用 @Mock 注解方式来创建 Mock 对象，使用该方式创需要注意的是要

在运行测试方法前使用MockitoAnnotations.initMocks(this) 或者单元测试类上加上

@ExtendWith(MockitoExtension.class) 注解

```java
@Slf4j
//@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    //创建一个实例，简单的说是这个Mock可以调用真实代码的方法，
    // 其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。(此注解表示这个对象需要被注入mock对象)
    @InjectMocks
    StudentServiceImpl studentService;

    //@Spy：对函数的调用均执行真正部分。
    //对函数的调用均执行mock（即虚假函数），不执行真正部分。(此注解会自动创建1个mock对象并注入到@InjectMocks对象中)
    @Mock
    StudentMapper studentMapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}
```



#### 执行顺序

**@BeforeClass -> @Before -> @Test -> @After -> @AfterClass;**

其他注解说明补充：
a. @Before注解 : junit测试类中的任意一个测试方法执行 前 都会执行此方法,该类型的方法可以被用来为测试方法初始化所需的资源。
b. @After注解  : junit测试类中的任意一个测试方法执行后 都会执行此方法, 即使被@Test 或 @Before修饰的测试方法抛出异常.该类型的方法被用来关闭由@Before注解修饰的测试方法打开的资源。
c. @Test 注解  : 测试方法包含了真正的测试代码，并且会被Junit应用为要测试的方法。可选的参数：expected 表示此测试方法执行后应该抛出的异常，（值是异常名）;timeout 检测测试方法的执行时间

### 3.3 测试案例

#### 1.service的测试

##### 1.1 普通方法

```java
//service代码
    @Override
    public Student getById(Integer id) {
        Student result = studentMapper.getById(id);
        log.info("stu:{}",result);
        return result;
    }


//不用理会方法中调用的函数结果，mock打桩后可以指定结果返回
    @Test
    public void testGetById() {
        Student student = new Student();
        student.setId(001);
        student.setClassNum(01);
        //when里面带的是条件，thenReturn里面表示的是返回结果
        Mockito.when(studentMapper.getById(Mockito.anyInt()))
                .thenReturn(student);
        Assertions.assertThat(studentService.getById(student.getId()).getClassNum()).isEqualTo(01);
    }
```

> 一个良好的单元测试应该更具实际的业务价值的。

##### 1.2 测试私有方法

```java
// service代码
    private String   privateMethod(){
        return "我是私有方法";
    }


@Test
public void testPrivateMethod(){
    Object result = ReflectionTestUtils.invokeMethod(studentService, "privateMethod", "str1",1);
    Assertions.assertThat(result).isEqualTo("我是私有方法");
}
```



##### 1.3 测试getter和setter

```java
public class UserTest {

    @InjectMocks
    User user;
  
    @Before
    public void init() {
        user = User.builder().id(1).gender(1).name("qijian").age(18).build();
    }

    //测试getter方法
    @Test
    public void testGetName() {
        Object result = ReflectionTestUtils.invokeMethod(user, "getName");
        Assertions.assertThat(result).isEqualTo("qijian");
    }

    //测试setter方法
    @Test
    public void testSetName() {
        Object result = ReflectionTestUtils.invokeMethod(user, "setName","tom");
        Assertions.assertThat(user.getName()).isEqualTo("tom");
    }
}
```



#### 2. controller测试

```java
@RunWith(MockitoJUnitRunner.Silent.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController = new StudentController();

    @Mock
    private StudentService fakeStudentService;

    // 初始化MockMvc对象
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

    private static Student student;

    @BeforeClass
    public static void init(){
        student = Student.builder().id(1).num("001").classNum(4).dormitoryNum(202).userId(1).build();
    }
    
    /*方法:GET
    URL:http://127.0.0.1:8100/student/getByNum?num=5005
    */
    @Test
    public void testGetByNum() throws Exception {
        Student fakeStudent = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
        Mockito.when(fakeStudentService.getById(fakeStudent.getId())).thenReturn(fakeStudent);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/getByNum?num=002")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*方法:GET
    URL:http://127.0.0.1:8100/student/getById?id=1
    */
    @Test
    public void testGetById() throws Exception {
        Student fakeStudent = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
        Mockito.when(fakeStudentService.getByNum(fakeStudent.getNum())).thenReturn(fakeStudent);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/getById?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*方法:POST
    URL:http://127.0.0.1:8100/student/save
    */
    @Test
    public void testSave() throws Exception {
        Student fakeStudent = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
        Mockito.when(fakeStudentService.getByNum(fakeStudent.getNum())).thenReturn(fakeStudent);
        mockMvc.perform(MockMvcRequestBuilders.post("/student/save")
                        .content(JSON.toJSONString(student))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

```



> * 1、mockMvc.perform执行一个请求。
> * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
> * 3、ResultActions.param添加请求传值
> * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
> * 5、ResultActions.andExpect添加执行完成后的断言。
> * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
> * 7、ResultActions.andReturn表示执行完成后返回相应的结果。

## 5. PowerMock的使用

​		如今比较流行的Mock工具都有一个共同的缺点：不能mock静态、final、私有方法等。这时就需要使用powerMock了。

```java
/**
 * @author mahuahong
 * @Date 2023/3/21 11:29
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ StudentServiceImpl.class,XxxUtils.class })
public class StudentService2Test {

    @InjectMocks
    private StudentService studentService = PowerMockito.spy(new StudentServiceImpl());


    @Test
    public void testMethodE1() throws Exception {
        PowerMockito.when(studentService,"privateMethod").thenReturn("private");
        String s = studentService.methodE(new Student());
        Assertions.assertEquals("privateMethod",s);
    }

    @Test
    public void testStaticMethod_withXxxMethod_returnFalse(){
//      为类的所有方法启用静态模拟。
        PowerMockito.mockStatic(XxxUtils.class);
        // mock掉对XxxUtils的静态调用
        PowerMockito.when(XxxUtils.xxxMethod(true)).thenReturn(false);
        boolean b = StudentServiceImpl.staticMethod(true);
        Assertions.assertEquals(b,false);
    }

    @Test
    public void testStaticMethod_withXxxMethod_returnTrue(){
//      为类的所有方法启用静态模拟。
        PowerMockito.mockStatic(XxxUtils.class);
        // mock掉对XxxUtils的静态调用
        PowerMockito.when(XxxUtils.xxxMethod(Mockito.anyBoolean())).thenReturn(true);
        boolean b = StudentServiceImpl.staticMethod(false);
        Assertions.assertEquals(b,true);
    }

}
```



## 6. 使用H2做持久层的单元测试

​		H2 是一个使用 Java 编写的数据库，支持内存、文件等多种模式，经常用于项目的测试环境。

```java
@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(properties = {
        "spring.datasource.schema = classpath:db/schema-h2.sql"
})
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    private User user;

    @Before
    public void init(){
        user = User.builder().id(6).name("qijian").age(18).gender(1).build();
        userMapper.add(user);
    }

    @Test
    public void testList(){
        List<User> list = userMapper.list();
        Assert.assertEquals(list.size(),4);
    }

    @Test
    public void testgetById(){
        User result = userMapper.getById(6);
        Assert.assertEquals(result.getName(),"qijian");

    }
}
```

Note:

采用内存数据库进行mybatis mapper单元测试优点：对实际的数据库无任何影响;确点：必须提供h2初始化语句，同时依赖于h2的 sdk



# 四.写单元测试需要遵守一些习惯

### 文件命名规范：

- 单元测试包目录：于被测试类对应包下
- 类名：[被测类]Test
- 方法名：test[被测方法]*[测试场景]*[预期行为]

![image-20230320001441620](https://qijian-1301807797.cos.ap-guangzhou.myqcloud.com/markdown/mockito_%E5%91%BD%E5%90%8D%E8%A7%84%E8%8C%83(0.1).png)

![image-20230320000637856](https://qijian-1301807797.cos.ap-guangzhou.myqcloud.com/markdown/mockito.%E5%91%BD%E5%90%8D%E8%A7%84%E8%8C%83_1.png)

### 单测规范

​		按照业务场景或条件来编写test

```java
//service代码
    @Override
    public ResponseData<List<User>> listForLessThan(User user){
        if (Objects.isNull(user)){
            return ResponseData.errorWithMeg("error");
        }
        if (user.getAge() == null){
            return ResponseData.errorWithMeg("输入的age不能为bull");
        }
        if (user.getAge() <= 0 || user.getAge() > 120){
            return ResponseData.errorWithMeg("输入的age需要大于0");
        }
        List<User> userList = userMapper.listForLessThan(user.getAge());
        return ResponseData.ok("成功",userList);
    }



/**
 * 案例：模拟没有传递参数的情况
 */
@Test
public void testListForLessThan_UserIsNull(){
    Mockito.when(userMapper.listForLessThan(Mockito.anyInt())).thenReturn(new ArrayList<>());
    ResponseData<List<User>> responseData = testService.listForLessThan(null);
    Assertions.assertThat(responseData.getCode()).isEqualTo(500);
}


/**
 * 案例：模拟没有传递年龄字段的值
 */
@Test
public void testListForLessThan_AgeIsNull(){
    Mockito.when(userMapper.listForLessThan(Mockito.anyInt())).thenReturn(new ArrayList<>());
    User user1 = User.builder().name("qijian").build();
    ResponseData<List<User>> responseData = testService.listForLessThan(user1);
    Assertions.assertThat(responseData.getCode()).isEqualTo(500);
}

/**
 * 案例：模拟传入的年龄的值小于等于0 或大于 120的情况
 */
@Test
public void testListForLessThan_AgeIsIllegal(){
    Mockito.when(userMapper.listForLessThan(Mockito.anyInt())).thenReturn(new ArrayList<>());
    User user1 = User.builder().name("qijian").age(121).build();
    ResponseData<List<User>> responseData = testService.listForLessThan(user1);
    Assertions.assertThat(responseData.getCode()).isEqualTo(500);
}

/**
 * 成功案例
 */
@Test
public void testListForLessThan_Ok(){
    User user0 = User.builder().id(0).name("qijian").age(18).gender(1).build();

    //如果入参是自定义的类对象，则需要利用Mockito.any()来进行，也可以自己new出来一个新类来进行：
    Mockito.when(userMapper.listForLessThan(Mockito.anyInt())).thenReturn(new ArrayList<>());
    User user1 = User.builder().name("qijian").age(18).build();
    ResponseData<List<User>> responseData = testService.listForLessThan(user0);
    Assertions.assertThat(responseData.getCode()).isEqualTo(200);
}
```







# 五.创建测试套件

### *Suite*

​		创建套件很容易。只需添加类的注释，然后开始在套件中包含或排除测试类和方法。`@Suite`当我们想要运行套件时，只需将其作为普通的 JUnit 测试类运行，它将执行套件中所有包含的测试。

```java
@RunWith(Suite.class)
@Suite.SuiteClasses({StudentControllerTest.class, UserServiceImplTest.class})
public class MockitoServiceTestSuit {
}
```



项目地址：[点击](https://github.com/qijianyeah/mockito-demo)



# 自动化工具介绍

我们是否需要从头开始一行行代码写？不用的，有些插件可以帮忙生成部分代码

生成代码的插件

1. idea自带的生成功能
2. testme插件
3. squaretest插件，这里就不展开讲了
4. chatgpt

本地查看覆盖率



# 六. 总结

1.单元测试需要FIRST原则（隔离，可重复，自我验证和及时）。

2.不同的业务场景或条件应该有一个Test。



参考资料：

[1]: https://www.jianshu.com/p/1703f72ab8b8	"提高单元测试覆盖率的意义与价值；"
[2]: https://zhuanlan.zhihu.com/p/519206940	"测试金字塔"
[3]: https://juejin.cn/post/7085568402470731789	"单元测试中的 FIRST 原则"
[4]: https://github.com/powermock/powermock/wiki/Getting-Started	"powermock"
[5]: http://www.h2database.com/html/main.html	"H2 Database Engine"

