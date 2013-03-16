package org.jboss.errai.marshalling.client.api;

import com.armandorv.cnpd.web.shared.exception.AlreadyConnectedException;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.exception.EjbLockupException;
import com.armandorv.cnpd.web.shared.exception.GoogleAccessException;
import com.armandorv.cnpd.web.shared.exception.IllegalStatefullCallException;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.exception.PresentationException;
import com.armandorv.cnpd.web.shared.exception.ServersideException;
import com.armandorv.cnpd.web.shared.exception.UnAuthenticatedUserException;
import com.armandorv.cnpd.web.shared.exception.UnconnectedUserException;
import com.armandorv.cnpd.web.shared.model.ChatMessage;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.GDocsResource;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.armandorv.cnpd.web.shared.model.MilestoneInfo;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.ReferenceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.armandorv.cnpd.web.shared.model.TaskInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.model.ValidationResponse;
import com.armandorv.cnpd.web.shared.model.VoteInfo;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.jboss.errai.bus.client.api.base.MessageDeliveryFailure;
import org.jboss.errai.bus.client.api.base.TransportIOException;
import org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent;
import org.jboss.errai.marshalling.client.api.json.EJArray;
import org.jboss.errai.marshalling.client.api.json.EJObject;
import org.jboss.errai.marshalling.client.api.json.EJValue;
import org.jboss.errai.marshalling.client.marshallers.BigDecimalMarshaller;
import org.jboss.errai.marshalling.client.marshallers.BigIntegerMarshaller;
import org.jboss.errai.marshalling.client.marshallers.BooleanMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ByteMarshaller;
import org.jboss.errai.marshalling.client.marshallers.CharacterMarshaller;
import org.jboss.errai.marshalling.client.marshallers.DateMarshaller;
import org.jboss.errai.marshalling.client.marshallers.DoubleMarshaller;
import org.jboss.errai.marshalling.client.marshallers.FloatMarshaller;
import org.jboss.errai.marshalling.client.marshallers.IntegerMarshaller;
import org.jboss.errai.marshalling.client.marshallers.LinkedListMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ListMarshaller;
import org.jboss.errai.marshalling.client.marshallers.LongMarshaller;
import org.jboss.errai.marshalling.client.marshallers.MapMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ObjectMarshaller;
import org.jboss.errai.marshalling.client.marshallers.PriorityQueueMarshaller;
import org.jboss.errai.marshalling.client.marshallers.QualifyingMarshallerWrapper;
import org.jboss.errai.marshalling.client.marshallers.QueueMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SQLDateMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SetMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ShortMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SortedMapMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SortedSetMarshaller;
import org.jboss.errai.marshalling.client.marshallers.StringBufferMarshaller;
import org.jboss.errai.marshalling.client.marshallers.StringBuilderMarshaller;
import org.jboss.errai.marshalling.client.marshallers.StringMarshaller;
import org.jboss.errai.marshalling.client.marshallers.TimeMarshaller;
import org.jboss.errai.marshalling.client.marshallers.TimestampMarshaller;

public class MarshallerFactoryImpl implements MarshallerFactory {
  private Map<String, Marshaller> marshallers = new HashMap<String, Marshaller>();
  private LongMarshaller java_lang_Long;
  private ListMarshaller java_util_List;
  private SortedSetMarshaller java_util_SortedSet;
  private DateMarshaller java_util_Date;
  private FloatMarshaller java_lang_Float;
  private SetMarshaller java_util_Set;
  private StringMarshaller java_lang_String;
  private QualifyingMarshallerWrapper<LinkedHashMap> java_util_LinkedHashMap;
  private SetMarshaller java_util_AbstractSet;
  private QueueMarshaller java_util_Queue;
  private QueueMarshaller java_util_AbstractQueue;
  private QualifyingMarshallerWrapper<Map> java_util_Map;
  private ShortMarshaller java_lang_Short;
  private DoubleMarshaller java_lang_Double;
  private BigDecimalMarshaller java_math_BigDecimal;
  private BooleanMarshaller java_lang_Boolean;
  private StringBuilderMarshaller java_lang_StringBuilder;
  private ObjectMarshaller java_lang_Object;
  private QualifyingMarshallerWrapper<SortedMap> java_util_SortedMap;
  private QualifyingMarshallerWrapper<AbstractMap> java_util_AbstractMap;
  private IntegerMarshaller java_lang_Integer;
  private ListMarshaller java_util_Vector;
  private ByteMarshaller java_lang_Byte;
  private CharacterMarshaller java_lang_Character;
  private QualifyingMarshallerWrapper<HashMap> java_util_HashMap;
  private ListMarshaller java_util_ArrayList;
  private SortedSetMarshaller java_util_TreeSet;
  private ListMarshaller java_util_AbstractList;
  private StringBufferMarshaller java_lang_StringBuffer;
  private QualifyingMarshallerWrapper<TreeMap> java_util_TreeMap;
  private BigIntegerMarshaller java_math_BigInteger;
  private SetMarshaller java_util_HashSet;
  private SQLDateMarshaller java_sql_Date;
  private LinkedListMarshaller java_util_LinkedList;
  private TimestampMarshaller java_sql_Timestamp;
  private SetMarshaller java_util_LinkedHashSet;
  private TimeMarshaller java_sql_Time;
  private ListMarshaller java_util_Stack;
  private PriorityQueueMarshaller java_util_PriorityQueue;
  private QualifyingMarshallerWrapper<StackTraceElement[]> arrayOf_java_lang_StackTraceElement_D1;
  private Marshaller<Throwable> java_lang_Throwable;
  private Marshaller<UserInfo> com_armandorv_cnpd_web_shared_model_UserInfo;
  private Marshaller<EmptyStackException> java_util_EmptyStackException;
  private Marshaller<Kind> com_armandorv_cnpd_web_shared_model_ResourceInfo$Kind;
  private Marshaller<DiscussionInfo> com_armandorv_cnpd_web_shared_model_DiscussionInfo;
  private Marshaller<VoteInfo> com_armandorv_cnpd_web_shared_model_VoteInfo;
  private Marshaller<UnsupportedOperationException> java_lang_UnsupportedOperationException;
  private Marshaller<NullPointerException> java_lang_NullPointerException;
  private Marshaller<ClassCastException> java_lang_ClassCastException;
  private Marshaller<BusReadyEvent> org_jboss_errai_enterprise_client_cdi_events_BusReadyEvent;
  private Marshaller<GoogleAccessException> com_armandorv_cnpd_web_shared_exception_GoogleAccessException;
  private Marshaller<IllegalStatefullCallException> com_armandorv_cnpd_web_shared_exception_IllegalStatefullCallException;
  private Marshaller<ReferenceInfo> com_armandorv_cnpd_web_shared_model_ReferenceInfo;
  private Marshaller<ArithmeticException> java_lang_ArithmeticException;
  private Marshaller<UnAuthenticatedUserException> com_armandorv_cnpd_web_shared_exception_UnAuthenticatedUserException;
  private Marshaller<ResourceInfo> com_armandorv_cnpd_web_shared_model_ResourceInfo;
  private Marshaller<TransportIOException> org_jboss_errai_bus_client_api_base_TransportIOException;
  private Marshaller<PresentationException> com_armandorv_cnpd_web_shared_exception_PresentationException;
  private Marshaller<ArrayStoreException> java_lang_ArrayStoreException;
  private Marshaller<ChatMessage> com_armandorv_cnpd_web_shared_model_ChatMessage;
  private Marshaller<RuntimeException> java_lang_RuntimeException;
  private Marshaller<MessageDeliveryFailure> org_jboss_errai_bus_client_api_base_MessageDeliveryFailure;
  private Marshaller<StringIndexOutOfBoundsException> java_lang_StringIndexOutOfBoundsException;
  private Marshaller<MappingErrorException> com_armandorv_cnpd_web_shared_exception_MappingErrorException;
  private Marshaller<MilestoneInfo> com_armandorv_cnpd_web_shared_model_MilestoneInfo;
  private Marshaller<ClientsideException> com_armandorv_cnpd_web_shared_exception_ClientsideException;
  private Marshaller<MessageInfo> com_armandorv_cnpd_web_shared_model_MessageInfo;
  private Marshaller<IdNameGenericInfo> com_armandorv_cnpd_web_shared_model_IdNameGenericInfo;
  private Marshaller<EjbLockupException> com_armandorv_cnpd_web_shared_exception_EjbLockupException;
  private Marshaller<UnconnectedUserException> com_armandorv_cnpd_web_shared_exception_UnconnectedUserException;
  private Marshaller<MeetingInfo> com_armandorv_cnpd_web_shared_model_MeetingInfo;
  private Marshaller<ValidationResponse> com_armandorv_cnpd_web_shared_model_ValidationResponse;
  private Marshaller<IndexOutOfBoundsException> java_lang_IndexOutOfBoundsException;
  private Marshaller<StackTraceElement> java_lang_StackTraceElement;
  private Marshaller<ProjectInfo> com_armandorv_cnpd_web_shared_model_ProjectInfo;
  private Marshaller<IllegalArgumentException> java_lang_IllegalArgumentException;
  private Marshaller<NegativeArraySizeException> java_lang_NegativeArraySizeException;
  private Marshaller<AssertionError> java_lang_AssertionError;
  private Marshaller<ConcurrentModificationException> java_util_ConcurrentModificationException;
  private Marshaller<ContactInfo> com_armandorv_cnpd_web_shared_model_ContactInfo;
  private Marshaller<com.armandorv.cnpd.web.shared.model.NotificationInfo.Kind> com_armandorv_cnpd_web_shared_model_NotificationInfo$Kind;
  private Marshaller<NotificationInfo> com_armandorv_cnpd_web_shared_model_NotificationInfo;
  private Marshaller<AlreadyConnectedException> com_armandorv_cnpd_web_shared_exception_AlreadyConnectedException;
  private Marshaller<IOException> java_io_IOException;
  private Marshaller<ServersideException> com_armandorv_cnpd_web_shared_exception_ServersideException;
  private Marshaller<TaskInfo> com_armandorv_cnpd_web_shared_model_TaskInfo;
  private Marshaller<GDocsResource> com_armandorv_cnpd_web_shared_model_GDocsResource;
  private QualifyingMarshallerWrapper<Object[]> arrayOf_java_lang_Object_D1;
  private QualifyingMarshallerWrapper<String[]> arrayOf_java_lang_String_D1;
  private QualifyingMarshallerWrapper<int[]> arrayOf_int_D1;
  private QualifyingMarshallerWrapper<long[]> arrayOf_long_D1;
  private QualifyingMarshallerWrapper<double[]> arrayOf_double_D1;
  private QualifyingMarshallerWrapper<float[]> arrayOf_float_D1;
  private QualifyingMarshallerWrapper<short[]> arrayOf_short_D1;
  private QualifyingMarshallerWrapper<boolean[]> arrayOf_boolean_D1;
  private QualifyingMarshallerWrapper<byte[]> arrayOf_byte_D1;
  private QualifyingMarshallerWrapper<Integer[]> arrayOf_java_lang_Integer_D1;
  private QualifyingMarshallerWrapper<Long[]> arrayOf_java_lang_Long_D1;
  private QualifyingMarshallerWrapper<Double[]> arrayOf_java_lang_Double_D1;
  private QualifyingMarshallerWrapper<Float[]> arrayOf_java_lang_Float_D1;
  private QualifyingMarshallerWrapper<Short[]> arrayOf_java_lang_Short_D1;
  private QualifyingMarshallerWrapper<Boolean[]> arrayOf_java_lang_Boolean_D1;
  private QualifyingMarshallerWrapper<Byte[]> arrayOf_java_lang_Byte_D1;
  public MarshallerFactoryImpl() {
    java_lang_Long = new LongMarshaller();
    marshallers.put("java.lang.Long", java_lang_Long);
    java_util_List = new ListMarshaller();
    marshallers.put("java.util.List", java_util_List);
    marshallers.put("java.util.Collections$SynchronizedRandomAccessList", java_util_List);
    marshallers.put("java.util.Collections$UnmodifiableRandomAccessList", java_util_List);
    marshallers.put("java.util.Stack", java_util_List);
    marshallers.put("java.util.Vector", java_util_List);
    marshallers.put("java.util.ArrayList", java_util_List);
    marshallers.put("java.util.Collections$SingletonList", java_util_List);
    marshallers.put("java.util.Collections$SynchronizedList", java_util_List);
    marshallers.put("java.util.Collections$UnmodifiableList", java_util_List);
    marshallers.put("java.util.Collections$EmptyList", java_util_List);
    marshallers.put("java.util.Arrays$ArrayList", java_util_List);
    marshallers.put("java.util.AbstractList", java_util_List);
    java_util_SortedSet = new SortedSetMarshaller();
    marshallers.put("java.util.SortedSet", java_util_SortedSet);
    marshallers.put("java.util.Collections$UnmodifiableSortedSet", java_util_SortedSet);
    marshallers.put("java.util.TreeSet", java_util_SortedSet);
    marshallers.put("java.util.Collections$SynchronizedSortedSet", java_util_SortedSet);
    java_util_Date = new DateMarshaller();
    marshallers.put("java.util.Date", java_util_Date);
    java_lang_Float = new FloatMarshaller();
    marshallers.put("java.lang.Float", java_lang_Float);
    java_util_Set = new SetMarshaller();
    marshallers.put("java.util.Set", java_util_Set);
    marshallers.put("java.util.Collections$SynchronizedSet", java_util_Set);
    marshallers.put("java.util.Collections$UnmodifiableSet", java_util_Set);
    marshallers.put("java.util.Collections$EmptySet", java_util_Set);
    marshallers.put("java.util.SortedSet", java_util_Set);
    marshallers.put("java.util.Collections$SingletonSet", java_util_Set);
    marshallers.put("java.util.AbstractSet", java_util_Set);
    marshallers.put("java.util.HashSet", java_util_Set);
    marshallers.put("java.util.LinkedHashSet", java_util_Set);
    java_lang_String = new StringMarshaller();
    marshallers.put("java.lang.String", java_lang_String);
    java_util_LinkedHashMap = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.LinkedHashMap", java_util_LinkedHashMap);
    java_util_AbstractSet = new SetMarshaller();
    marshallers.put("java.util.AbstractSet", java_util_AbstractSet);
    java_util_Queue = new QueueMarshaller();
    marshallers.put("java.util.Queue", java_util_Queue);
    marshallers.put("java.util.AbstractQueue", java_util_Queue);
    java_util_AbstractQueue = new QueueMarshaller();
    marshallers.put("java.util.AbstractQueue", java_util_AbstractQueue);
    java_util_Map = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.Map", java_util_Map);
    marshallers.put("java.util.Collections$SingletonMap", java_util_Map);
    marshallers.put("java.util.AbstractMap", java_util_Map);
    marshallers.put("java.util.Collections$SynchronizedMap", java_util_Map);
    marshallers.put("java.util.HashMap", java_util_Map);
    marshallers.put("java.util.Collections$UnmodifiableMap", java_util_Map);
    marshallers.put("java.util.Collections$EmptyMap", java_util_Map);
    marshallers.put("java.util.LinkedHashMap", java_util_Map);
    java_lang_Short = new ShortMarshaller();
    marshallers.put("java.lang.Short", java_lang_Short);
    java_lang_Double = new DoubleMarshaller();
    marshallers.put("java.lang.Double", java_lang_Double);
    java_math_BigDecimal = new BigDecimalMarshaller();
    marshallers.put("java.math.BigDecimal", java_math_BigDecimal);
    java_lang_Boolean = new BooleanMarshaller();
    marshallers.put("java.lang.Boolean", java_lang_Boolean);
    java_lang_StringBuilder = new StringBuilderMarshaller();
    marshallers.put("java.lang.StringBuilder", java_lang_StringBuilder);
    java_lang_Object = new ObjectMarshaller();
    marshallers.put("java.lang.Object", java_lang_Object);
    java_util_SortedMap = new QualifyingMarshallerWrapper(new SortedMapMarshaller());
    marshallers.put("java.util.SortedMap", java_util_SortedMap);
    marshallers.put("java.util.Collections$SynchronizedSortedMap", java_util_SortedMap);
    marshallers.put("java.util.Collections$UnmodifiableSortedMap", java_util_SortedMap);
    marshallers.put("java.util.TreeMap", java_util_SortedMap);
    java_util_AbstractMap = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.AbstractMap", java_util_AbstractMap);
    java_lang_Integer = new IntegerMarshaller();
    marshallers.put("java.lang.Integer", java_lang_Integer);
    java_util_Vector = new ListMarshaller();
    marshallers.put("java.util.Vector", java_util_Vector);
    java_lang_Byte = new ByteMarshaller();
    marshallers.put("java.lang.Byte", java_lang_Byte);
    java_lang_Character = new CharacterMarshaller();
    marshallers.put("java.lang.Character", java_lang_Character);
    java_util_HashMap = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.HashMap", java_util_HashMap);
    java_util_ArrayList = new ListMarshaller();
    marshallers.put("java.util.ArrayList", java_util_ArrayList);
    java_util_TreeSet = new SortedSetMarshaller();
    marshallers.put("java.util.TreeSet", java_util_TreeSet);
    java_util_AbstractList = new ListMarshaller();
    marshallers.put("java.util.AbstractList", java_util_AbstractList);
    java_lang_StringBuffer = new StringBufferMarshaller();
    marshallers.put("java.lang.StringBuffer", java_lang_StringBuffer);
    java_util_TreeMap = new QualifyingMarshallerWrapper(new SortedMapMarshaller());
    marshallers.put("java.util.TreeMap", java_util_TreeMap);
    java_math_BigInteger = new BigIntegerMarshaller();
    marshallers.put("java.math.BigInteger", java_math_BigInteger);
    java_util_HashSet = new SetMarshaller();
    marshallers.put("java.util.HashSet", java_util_HashSet);
    java_sql_Date = new SQLDateMarshaller();
    marshallers.put("java.sql.Date", java_sql_Date);
    java_util_LinkedList = new LinkedListMarshaller();
    marshallers.put("java.util.LinkedList", java_util_LinkedList);
    java_sql_Timestamp = new TimestampMarshaller();
    marshallers.put("java.sql.Timestamp", java_sql_Timestamp);
    java_util_LinkedHashSet = new SetMarshaller();
    marshallers.put("java.util.LinkedHashSet", java_util_LinkedHashSet);
    java_sql_Time = new TimeMarshaller();
    marshallers.put("java.sql.Time", java_sql_Time);
    java_util_Stack = new ListMarshaller();
    marshallers.put("java.util.Stack", java_util_Stack);
    java_util_PriorityQueue = new PriorityQueueMarshaller();
    marshallers.put("java.util.PriorityQueue", java_util_PriorityQueue);
    arrayOf_java_lang_StackTraceElement_D1 = new QualifyingMarshallerWrapper(new Marshaller<StackTraceElement[]>() {
      private StackTraceElement[] _demarshall1(EJArray a0, MarshallingSession a1) {
        StackTraceElement[] newArray = new StackTraceElement[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_StackTraceElement.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(StackTraceElement[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_StackTraceElement.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return StackTraceElement.class;
      }
      public StackTraceElement[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(StackTraceElement[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.StackTraceElement;", arrayOf_java_lang_StackTraceElement_D1);
    java_lang_Throwable = new Marshaller<Throwable>() {
      public Class getTypeHandled() {
        return Throwable.class;
      }
      public Throwable demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(Throwable.class, objId);
          }
          Throwable entity = new Throwable(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.Throwable", t);
        }
      }
      public String marshall(Throwable a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.Throwable\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.Throwable\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.Throwable", java_lang_Throwable);
    com_armandorv_cnpd_web_shared_model_UserInfo = new Marshaller<UserInfo>() {
      public Class getTypeHandled() {
        return UserInfo.class;
      }
      public UserInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(UserInfo.class, objId);
          }
          UserInfo entity = new UserInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("lastname1")) && (!obj.get("lastname1").isNull())) {
            entity.setLastname1(java_lang_String.demarshall(obj.get("lastname1"), a1));
          }
          if ((obj.containsKey("lastname2")) && (!obj.get("lastname2").isNull())) {
            entity.setLastname2(java_lang_String.demarshall(obj.get("lastname2"), a1));
          }
          if ((obj.containsKey("fullName")) && (!obj.get("fullName").isNull())) {
            entity.setFullName(java_lang_String.demarshall(obj.get("fullName"), a1));
          }
          if ((obj.containsKey("username")) && (!obj.get("username").isNull())) {
            entity.setUsername(java_lang_String.demarshall(obj.get("username"), a1));
          }
          if ((obj.containsKey("birthday")) && (!obj.get("birthday").isNull())) {
            entity.setBirthday((Date) java_lang_Object.demarshall(obj.get("birthday"), a1));
          }
          if ((obj.containsKey("city")) && (!obj.get("city").isNull())) {
            entity.setCity(java_lang_String.demarshall(obj.get("city"), a1));
          }
          if ((obj.containsKey("degrees")) && (!obj.get("degrees").isNull())) {
            entity.setDegrees(java_util_List.demarshall(obj.get("degrees"), a1));
          }
          if ((obj.containsKey("jobs")) && (!obj.get("jobs").isNull())) {
            entity.setJobs(java_util_List.demarshall(obj.get("jobs"), a1));
          }
          if ((obj.containsKey("webSite")) && (!obj.get("webSite").isNull())) {
            entity.setWebSite(java_lang_String.demarshall(obj.get("webSite"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.UserInfo", t);
        }
      }
      public String marshall(UserInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.UserInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(1536).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.UserInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"lastname1\" : ").append(java_lang_String.marshall(a0.getLastname1(), a1)).append(",").append("\"lastname2\" : ").append(java_lang_String.marshall(a0.getLastname2(), a1)).append(",").append("\"fullName\" : ").append(java_lang_String.marshall(a0.getFullName(), a1)).append(",").append("\"username\" : ").append(java_lang_String.marshall(a0.getUsername(), a1)).append(",").append("\"birthday\" : ").append(java_lang_Object.marshall(a0.getBirthday(), a1)).append(",").append("\"city\" : ").append(java_lang_String.marshall(a0.getCity(), a1)).append(",").append("\"degrees\" : ").append(java_util_List.marshall(a0.getDegrees(), a1)).append(",").append("\"jobs\" : ").append(java_util_List.marshall(a0.getJobs(), a1)).append(",").append("\"webSite\" : ").append(java_lang_String.marshall(a0.getWebSite(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.UserInfo", com_armandorv_cnpd_web_shared_model_UserInfo);
    java_util_EmptyStackException = new Marshaller<EmptyStackException>() {
      public Class getTypeHandled() {
        return EmptyStackException.class;
      }
      public EmptyStackException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(EmptyStackException.class, objId);
          }
          EmptyStackException entity = new EmptyStackException();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.util.EmptyStackException", t);
        }
      }
      public String marshall(EmptyStackException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.util.EmptyStackException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.util.EmptyStackException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.util.EmptyStackException", java_util_EmptyStackException);
    com_armandorv_cnpd_web_shared_model_ResourceInfo$Kind = new Marshaller<Kind>() {
      public Class getTypeHandled() {
        return Kind.class;
      }
      public Kind demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          Kind entity = obj != null ? Enum.valueOf(Kind.class, obj.get("^EnumStringValue").isString().stringValue()) : null;
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.ResourceInfo$Kind", t);
        }
      }
      public String marshall(Kind a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        return new StringBuilder(256).append(a0 != null ? new StringBuilder(64).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ResourceInfo$Kind\",\"^EnumStringValue\":\"").append(a0.name()).append("\"}") : "null").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.ResourceInfo$Kind", com_armandorv_cnpd_web_shared_model_ResourceInfo$Kind);
    marshallers.put("com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind", com_armandorv_cnpd_web_shared_model_ResourceInfo$Kind);
    com_armandorv_cnpd_web_shared_model_DiscussionInfo = new Marshaller<DiscussionInfo>() {
      public Class getTypeHandled() {
        return DiscussionInfo.class;
      }
      public DiscussionInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(DiscussionInfo.class, objId);
          }
          DiscussionInfo entity = new DiscussionInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("title")) && (!obj.get("title").isNull())) {
            entity.setTitle(java_lang_String.demarshall(obj.get("title"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("open")) && (!obj.get("open").isNull())) {
            entity.setOpen((boolean) java_lang_Boolean.demarshall(obj.get("open"), a1));
          }
          if ((obj.containsKey("options")) && (!obj.get("options").isNull())) {
            entity.setOptions(java_util_List.demarshall(obj.get("options"), a1));
          }
          if ((obj.containsKey("votes")) && (!obj.get("votes").isNull())) {
            entity.setVotes(java_util_List.demarshall(obj.get("votes"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.DiscussionInfo", t);
        }
      }
      public String marshall(DiscussionInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.DiscussionInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(896).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.DiscussionInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"open\" : ").append(java_lang_Boolean.marshall(com_armandorv_cnpd_web_shared_model_DiscussionInfo_open(a0), a1)).append(",").append("\"options\" : ").append(java_util_List.marshall(a0.getOptions(), a1)).append(",").append("\"votes\" : ").append(java_util_List.marshall(a0.getVotes(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.DiscussionInfo", com_armandorv_cnpd_web_shared_model_DiscussionInfo);
    com_armandorv_cnpd_web_shared_model_VoteInfo = new Marshaller<VoteInfo>() {
      public Class getTypeHandled() {
        return VoteInfo.class;
      }
      public VoteInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(VoteInfo.class, objId);
          }
          VoteInfo entity = new VoteInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("userId")) && (!obj.get("userId").isNull())) {
            entity.setUserId((long) java_lang_Long.demarshall(obj.get("userId"), a1));
          }
          if ((obj.containsKey("voterName")) && (!obj.get("voterName").isNull())) {
            entity.setVoterName(java_lang_String.demarshall(obj.get("voterName"), a1));
          }
          if ((obj.containsKey("option")) && (!obj.get("option").isNull())) {
            entity.setOption(java_lang_String.demarshall(obj.get("option"), a1));
          }
          if ((obj.containsKey("argument")) && (!obj.get("argument").isNull())) {
            entity.setArgument(java_lang_String.demarshall(obj.get("argument"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.VoteInfo", t);
        }
      }
      public String marshall(VoteInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.VoteInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(640).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.VoteInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"userId\" : ").append(java_lang_Long.marshall(a0.getUserId(), a1)).append(",").append("\"voterName\" : ").append(java_lang_String.marshall(a0.getVoterName(), a1)).append(",").append("\"option\" : ").append(java_lang_String.marshall(a0.getOption(), a1)).append(",").append("\"argument\" : ").append(java_lang_String.marshall(a0.getArgument(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.VoteInfo", com_armandorv_cnpd_web_shared_model_VoteInfo);
    java_lang_UnsupportedOperationException = new Marshaller<UnsupportedOperationException>() {
      public Class getTypeHandled() {
        return UnsupportedOperationException.class;
      }
      public UnsupportedOperationException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(UnsupportedOperationException.class, objId);
          }
          UnsupportedOperationException entity = new UnsupportedOperationException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.UnsupportedOperationException", t);
        }
      }
      public String marshall(UnsupportedOperationException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.UnsupportedOperationException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.UnsupportedOperationException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.UnsupportedOperationException", java_lang_UnsupportedOperationException);
    java_lang_NullPointerException = new Marshaller<NullPointerException>() {
      public Class getTypeHandled() {
        return NullPointerException.class;
      }
      public NullPointerException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(NullPointerException.class, objId);
          }
          NullPointerException entity = new NullPointerException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.NullPointerException", t);
        }
      }
      public String marshall(NullPointerException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.NullPointerException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.NullPointerException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.NullPointerException", java_lang_NullPointerException);
    java_lang_ClassCastException = new Marshaller<ClassCastException>() {
      public Class getTypeHandled() {
        return ClassCastException.class;
      }
      public ClassCastException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ClassCastException.class, objId);
          }
          ClassCastException entity = new ClassCastException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.ClassCastException", t);
        }
      }
      public String marshall(ClassCastException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.ClassCastException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.ClassCastException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.ClassCastException", java_lang_ClassCastException);
    org_jboss_errai_enterprise_client_cdi_events_BusReadyEvent = new Marshaller<BusReadyEvent>() {
      public Class getTypeHandled() {
        return BusReadyEvent.class;
      }
      public BusReadyEvent demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(BusReadyEvent.class, objId);
          }
          BusReadyEvent entity = new BusReadyEvent();
          a1.recordObjectHash(objId, entity);
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent", t);
        }
      }
      public String marshall(BusReadyEvent a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent\",\"^ObjectID\":\"").append(objId).append("\"").append(",\"^InstantiateOnly\":true").append("}").toString();
      }
    };
    marshallers.put("org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent", org_jboss_errai_enterprise_client_cdi_events_BusReadyEvent);
    com_armandorv_cnpd_web_shared_exception_GoogleAccessException = new Marshaller<GoogleAccessException>() {
      public Class getTypeHandled() {
        return GoogleAccessException.class;
      }
      public GoogleAccessException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(GoogleAccessException.class, objId);
          }
          GoogleAccessException entity = new GoogleAccessException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.GoogleAccessException", t);
        }
      }
      public String marshall(GoogleAccessException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.GoogleAccessException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.GoogleAccessException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.GoogleAccessException", com_armandorv_cnpd_web_shared_exception_GoogleAccessException);
    com_armandorv_cnpd_web_shared_exception_IllegalStatefullCallException = new Marshaller<IllegalStatefullCallException>() {
      public Class getTypeHandled() {
        return IllegalStatefullCallException.class;
      }
      public IllegalStatefullCallException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(IllegalStatefullCallException.class, objId);
          }
          IllegalStatefullCallException entity = new IllegalStatefullCallException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.IllegalStatefullCallException", t);
        }
      }
      public String marshall(IllegalStatefullCallException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.IllegalStatefullCallException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.IllegalStatefullCallException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.IllegalStatefullCallException", com_armandorv_cnpd_web_shared_exception_IllegalStatefullCallException);
    com_armandorv_cnpd_web_shared_model_ReferenceInfo = new Marshaller<ReferenceInfo>() {
      public Class getTypeHandled() {
        return ReferenceInfo.class;
      }
      public ReferenceInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ReferenceInfo.class, objId);
          }
          ReferenceInfo entity = new ReferenceInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("url")) && (!obj.get("url").isNull())) {
            entity.setUrl(java_lang_String.demarshall(obj.get("url"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.ReferenceInfo", t);
        }
      }
      public String marshall(ReferenceInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ReferenceInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(512).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ReferenceInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"url\" : ").append(java_lang_String.marshall(a0.getUrl(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.ReferenceInfo", com_armandorv_cnpd_web_shared_model_ReferenceInfo);
    java_lang_ArithmeticException = new Marshaller<ArithmeticException>() {
      public Class getTypeHandled() {
        return ArithmeticException.class;
      }
      public ArithmeticException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ArithmeticException.class, objId);
          }
          ArithmeticException entity = new ArithmeticException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.ArithmeticException", t);
        }
      }
      public String marshall(ArithmeticException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.ArithmeticException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.ArithmeticException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.ArithmeticException", java_lang_ArithmeticException);
    com_armandorv_cnpd_web_shared_exception_UnAuthenticatedUserException = new Marshaller<UnAuthenticatedUserException>() {
      public Class getTypeHandled() {
        return UnAuthenticatedUserException.class;
      }
      public UnAuthenticatedUserException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(UnAuthenticatedUserException.class, objId);
          }
          UnAuthenticatedUserException entity = new UnAuthenticatedUserException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.UnAuthenticatedUserException", t);
        }
      }
      public String marshall(UnAuthenticatedUserException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.UnAuthenticatedUserException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.UnAuthenticatedUserException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.UnAuthenticatedUserException", com_armandorv_cnpd_web_shared_exception_UnAuthenticatedUserException);
    com_armandorv_cnpd_web_shared_model_ResourceInfo = new Marshaller<ResourceInfo>() {
      public Class getTypeHandled() {
        return ResourceInfo.class;
      }
      public ResourceInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ResourceInfo.class, objId);
          }
          ResourceInfo entity = new ResourceInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("kind")) && (!obj.get("kind").isNull())) {
            entity.setKind(obj.get("kind").isObject() != null ? Enum.valueOf(Kind.class, obj.get("kind").isObject().get("^EnumStringValue").isString().stringValue()) : null);
          }
          if ((obj.containsKey("url")) && (!obj.get("url").isNull())) {
            entity.setUrl(java_lang_String.demarshall(obj.get("url"), a1));
          }
          if ((obj.containsKey("checked")) && (!obj.get("checked").isNull())) {
            entity.setChecked((boolean) java_lang_Boolean.demarshall(obj.get("checked"), a1));
          }
          if ((obj.containsKey("childs")) && (!obj.get("childs").isNull())) {
            entity.setChilds(java_util_List.demarshall(obj.get("childs"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.ResourceInfo", t);
        }
      }
      public String marshall(ResourceInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ResourceInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(896).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ResourceInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"kind\" : ").append(a0.getKind() != null ? new StringBuilder(64).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ResourceInfo$Kind\",\"^EnumStringValue\":\"").append(a0.getKind().name()).append("\"}") : "null").append(",").append("\"url\" : ").append(java_lang_String.marshall(a0.getUrl(), a1)).append(",").append("\"checked\" : ").append(java_lang_Boolean.marshall(com_armandorv_cnpd_web_shared_model_ResourceInfo_checked(a0), a1)).append(",").append("\"childs\" : ").append(java_util_List.marshall(a0.getChilds(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.ResourceInfo", com_armandorv_cnpd_web_shared_model_ResourceInfo);
    org_jboss_errai_bus_client_api_base_TransportIOException = new Marshaller<TransportIOException>() {
      public Class getTypeHandled() {
        return TransportIOException.class;
      }
      public TransportIOException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(TransportIOException.class, objId);
          }
          TransportIOException entity = new TransportIOException(java_lang_String.demarshall(obj.get("message"), a1), java_lang_Integer.demarshall(obj.get("errorCode"), a1), java_lang_String.demarshall(obj.get("errorMessage"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.errai.bus.client.api.base.TransportIOException", t);
        }
      }
      public String marshall(TransportIOException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.TransportIOException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(4224).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.TransportIOException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"errorCode\" : ").append(java_lang_Integer.marshall(a0.errorCode(), a1)).append(",").append("\"errorMessage\" : ").append(java_lang_String.marshall(a0.getErrorMessage(), a1)).append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.errai.bus.client.api.base.TransportIOException", org_jboss_errai_bus_client_api_base_TransportIOException);
    com_armandorv_cnpd_web_shared_exception_PresentationException = new Marshaller<PresentationException>() {
      public Class getTypeHandled() {
        return PresentationException.class;
      }
      public PresentationException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(PresentationException.class, objId);
          }
          PresentationException entity = new PresentationException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.PresentationException", t);
        }
      }
      public String marshall(PresentationException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.PresentationException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.PresentationException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.PresentationException", com_armandorv_cnpd_web_shared_exception_PresentationException);
    java_lang_ArrayStoreException = new Marshaller<ArrayStoreException>() {
      public Class getTypeHandled() {
        return ArrayStoreException.class;
      }
      public ArrayStoreException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ArrayStoreException.class, objId);
          }
          ArrayStoreException entity = new ArrayStoreException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.ArrayStoreException", t);
        }
      }
      public String marshall(ArrayStoreException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.ArrayStoreException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.ArrayStoreException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.ArrayStoreException", java_lang_ArrayStoreException);
    com_armandorv_cnpd_web_shared_model_ChatMessage = new Marshaller<ChatMessage>() {
      public Class getTypeHandled() {
        return ChatMessage.class;
      }
      public ChatMessage demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ChatMessage.class, objId);
          }
          ChatMessage entity = new ChatMessage();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("from")) && (!obj.get("from").isNull())) {
            entity.setFrom(java_lang_String.demarshall(obj.get("from"), a1));
          }
          if ((obj.containsKey("to")) && (!obj.get("to").isNull())) {
            entity.setTo(java_lang_String.demarshall(obj.get("to"), a1));
          }
          if ((obj.containsKey("text")) && (!obj.get("text").isNull())) {
            entity.setText(java_lang_String.demarshall(obj.get("text"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.ChatMessage", t);
        }
      }
      public String marshall(ChatMessage a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ChatMessage\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(512).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ChatMessage\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"from\" : ").append(java_lang_String.marshall(a0.getFrom(), a1)).append(",").append("\"to\" : ").append(java_lang_String.marshall(a0.getTo(), a1)).append(",").append("\"text\" : ").append(java_lang_String.marshall(a0.getText(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.ChatMessage", com_armandorv_cnpd_web_shared_model_ChatMessage);
    java_lang_RuntimeException = new Marshaller<RuntimeException>() {
      public Class getTypeHandled() {
        return RuntimeException.class;
      }
      public RuntimeException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(RuntimeException.class, objId);
          }
          RuntimeException entity = new RuntimeException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.RuntimeException", t);
        }
      }
      public String marshall(RuntimeException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.RuntimeException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.RuntimeException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.RuntimeException", java_lang_RuntimeException);
    org_jboss_errai_bus_client_api_base_MessageDeliveryFailure = new Marshaller<MessageDeliveryFailure>() {
      public Class getTypeHandled() {
        return MessageDeliveryFailure.class;
      }
      public MessageDeliveryFailure demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(MessageDeliveryFailure.class, objId);
          }
          MessageDeliveryFailure entity = new MessageDeliveryFailure(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.errai.bus.client.api.base.MessageDeliveryFailure", t);
        }
      }
      public String marshall(MessageDeliveryFailure a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.MessageDeliveryFailure\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.MessageDeliveryFailure\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.errai.bus.client.api.base.MessageDeliveryFailure", org_jboss_errai_bus_client_api_base_MessageDeliveryFailure);
    java_lang_StringIndexOutOfBoundsException = new Marshaller<StringIndexOutOfBoundsException>() {
      public Class getTypeHandled() {
        return StringIndexOutOfBoundsException.class;
      }
      public StringIndexOutOfBoundsException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(StringIndexOutOfBoundsException.class, objId);
          }
          StringIndexOutOfBoundsException entity = new StringIndexOutOfBoundsException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.StringIndexOutOfBoundsException", t);
        }
      }
      public String marshall(StringIndexOutOfBoundsException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.StringIndexOutOfBoundsException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.StringIndexOutOfBoundsException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.StringIndexOutOfBoundsException", java_lang_StringIndexOutOfBoundsException);
    com_armandorv_cnpd_web_shared_exception_MappingErrorException = new Marshaller<MappingErrorException>() {
      public Class getTypeHandled() {
        return MappingErrorException.class;
      }
      public MappingErrorException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(MappingErrorException.class, objId);
          }
          MappingErrorException entity = new MappingErrorException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.MappingErrorException", t);
        }
      }
      public String marshall(MappingErrorException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.MappingErrorException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.MappingErrorException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.MappingErrorException", com_armandorv_cnpd_web_shared_exception_MappingErrorException);
    com_armandorv_cnpd_web_shared_model_MilestoneInfo = new Marshaller<MilestoneInfo>() {
      public Class getTypeHandled() {
        return MilestoneInfo.class;
      }
      public MilestoneInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(MilestoneInfo.class, objId);
          }
          MilestoneInfo entity = new MilestoneInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("date")) && (!obj.get("date").isNull())) {
            entity.setDate((Date) java_lang_Object.demarshall(obj.get("date"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.MilestoneInfo", t);
        }
      }
      public String marshall(MilestoneInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.MilestoneInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(512).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.MilestoneInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"date\" : ").append(java_lang_Object.marshall(a0.getDate(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.MilestoneInfo", com_armandorv_cnpd_web_shared_model_MilestoneInfo);
    com_armandorv_cnpd_web_shared_exception_ClientsideException = new Marshaller<ClientsideException>() {
      public Class getTypeHandled() {
        return ClientsideException.class;
      }
      public ClientsideException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ClientsideException.class, objId);
          }
          ClientsideException entity = new ClientsideException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.ClientsideException", t);
        }
      }
      public String marshall(ClientsideException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.ClientsideException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.ClientsideException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.ClientsideException", com_armandorv_cnpd_web_shared_exception_ClientsideException);
    com_armandorv_cnpd_web_shared_model_MessageInfo = new Marshaller<MessageInfo>() {
      public Class getTypeHandled() {
        return MessageInfo.class;
      }
      public MessageInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(MessageInfo.class, objId);
          }
          MessageInfo entity = new MessageInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("sender")) && (!obj.get("sender").isNull())) {
            entity.setSender(java_lang_String.demarshall(obj.get("sender"), a1));
          }
          if ((obj.containsKey("to")) && (!obj.get("to").isNull())) {
            entity.setTo(java_lang_String.demarshall(obj.get("to"), a1));
          }
          if ((obj.containsKey("content")) && (!obj.get("content").isNull())) {
            entity.setContent(java_lang_String.demarshall(obj.get("content"), a1));
          }
          if ((obj.containsKey("date")) && (!obj.get("date").isNull())) {
            entity.setDate((Date) java_lang_Object.demarshall(obj.get("date"), a1));
          }
          if ((obj.containsKey("read")) && (!obj.get("read").isNull())) {
            entity.setRead((boolean) java_lang_Boolean.demarshall(obj.get("read"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.MessageInfo", t);
        }
      }
      public String marshall(MessageInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.MessageInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(896).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.MessageInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"sender\" : ").append(java_lang_String.marshall(a0.getSender(), a1)).append(",").append("\"to\" : ").append(java_lang_String.marshall(a0.getTo(), a1)).append(",").append("\"content\" : ").append(java_lang_String.marshall(a0.getContent(), a1)).append(",").append("\"date\" : ").append(java_lang_Object.marshall(a0.getDate(), a1)).append(",").append("\"read\" : ").append(java_lang_Boolean.marshall(com_armandorv_cnpd_web_shared_model_MessageInfo_read(a0), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.MessageInfo", com_armandorv_cnpd_web_shared_model_MessageInfo);
    com_armandorv_cnpd_web_shared_model_IdNameGenericInfo = new Marshaller<IdNameGenericInfo>() {
      public Class getTypeHandled() {
        return IdNameGenericInfo.class;
      }
      public IdNameGenericInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(IdNameGenericInfo.class, objId);
          }
          IdNameGenericInfo entity = new IdNameGenericInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.IdNameGenericInfo", t);
        }
      }
      public String marshall(IdNameGenericInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.IdNameGenericInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(384).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.IdNameGenericInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.IdNameGenericInfo", com_armandorv_cnpd_web_shared_model_IdNameGenericInfo);
    com_armandorv_cnpd_web_shared_exception_EjbLockupException = new Marshaller<EjbLockupException>() {
      public Class getTypeHandled() {
        return EjbLockupException.class;
      }
      public EjbLockupException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(EjbLockupException.class, objId);
          }
          EjbLockupException entity = new EjbLockupException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.EjbLockupException", t);
        }
      }
      public String marshall(EjbLockupException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.EjbLockupException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.EjbLockupException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.EjbLockupException", com_armandorv_cnpd_web_shared_exception_EjbLockupException);
    com_armandorv_cnpd_web_shared_exception_UnconnectedUserException = new Marshaller<UnconnectedUserException>() {
      public Class getTypeHandled() {
        return UnconnectedUserException.class;
      }
      public UnconnectedUserException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(UnconnectedUserException.class, objId);
          }
          UnconnectedUserException entity = new UnconnectedUserException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.UnconnectedUserException", t);
        }
      }
      public String marshall(UnconnectedUserException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.UnconnectedUserException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.UnconnectedUserException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.UnconnectedUserException", com_armandorv_cnpd_web_shared_exception_UnconnectedUserException);
    com_armandorv_cnpd_web_shared_model_MeetingInfo = new Marshaller<MeetingInfo>() {
      public Class getTypeHandled() {
        return MeetingInfo.class;
      }
      public MeetingInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(MeetingInfo.class, objId);
          }
          MeetingInfo entity = new MeetingInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("title")) && (!obj.get("title").isNull())) {
            entity.setTitle(java_lang_String.demarshall(obj.get("title"), a1));
          }
          if ((obj.containsKey("place")) && (!obj.get("place").isNull())) {
            entity.setPlace(java_lang_String.demarshall(obj.get("place"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("date")) && (!obj.get("date").isNull())) {
            entity.setDate((Date) java_lang_Object.demarshall(obj.get("date"), a1));
          }
          if ((obj.containsKey("celebrated")) && (!obj.get("celebrated").isNull())) {
            entity.setCelebrated((boolean) java_lang_Boolean.demarshall(obj.get("celebrated"), a1));
          }
          if ((obj.containsKey("conclusions")) && (!obj.get("conclusions").isNull())) {
            entity.setConclusions(java_lang_String.demarshall(obj.get("conclusions"), a1));
          }
          if ((obj.containsKey("instigatorId")) && (!obj.get("instigatorId").isNull())) {
            entity.setInstigatorId((long) java_lang_Long.demarshall(obj.get("instigatorId"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.MeetingInfo", t);
        }
      }
      public String marshall(MeetingInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.MeetingInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(1152).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.MeetingInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append(",").append("\"place\" : ").append(java_lang_String.marshall(a0.getPlace(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"date\" : ").append(java_lang_Object.marshall(a0.getDate(), a1)).append(",").append("\"celebrated\" : ").append(java_lang_Boolean.marshall(com_armandorv_cnpd_web_shared_model_MeetingInfo_celebrated(a0), a1)).append(",").append("\"conclusions\" : ").append(java_lang_String.marshall(a0.getConclusions(), a1)).append(",").append("\"instigatorId\" : ").append(java_lang_Long.marshall(a0.getInstigatorId(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.MeetingInfo", com_armandorv_cnpd_web_shared_model_MeetingInfo);
    com_armandorv_cnpd_web_shared_model_ValidationResponse = new Marshaller<ValidationResponse>() {
      public Class getTypeHandled() {
        return ValidationResponse.class;
      }
      public ValidationResponse demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ValidationResponse.class, objId);
          }
          ValidationResponse entity = new ValidationResponse();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("positive")) && (!obj.get("positive").isNull())) {
            entity.setPositive((boolean) java_lang_Boolean.demarshall(obj.get("positive"), a1));
          }
          if ((obj.containsKey("message")) && (!obj.get("message").isNull())) {
            entity.setMessage(java_lang_String.demarshall(obj.get("message"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.ValidationResponse", t);
        }
      }
      public String marshall(ValidationResponse a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ValidationResponse\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(384).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ValidationResponse\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"positive\" : ").append(java_lang_Boolean.marshall(com_armandorv_cnpd_web_shared_model_ValidationResponse_positive(a0), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.ValidationResponse", com_armandorv_cnpd_web_shared_model_ValidationResponse);
    java_lang_IndexOutOfBoundsException = new Marshaller<IndexOutOfBoundsException>() {
      public Class getTypeHandled() {
        return IndexOutOfBoundsException.class;
      }
      public IndexOutOfBoundsException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(IndexOutOfBoundsException.class, objId);
          }
          IndexOutOfBoundsException entity = new IndexOutOfBoundsException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.IndexOutOfBoundsException", t);
        }
      }
      public String marshall(IndexOutOfBoundsException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.IndexOutOfBoundsException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.IndexOutOfBoundsException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.IndexOutOfBoundsException", java_lang_IndexOutOfBoundsException);
    java_lang_StackTraceElement = new Marshaller<StackTraceElement>() {
      public Class getTypeHandled() {
        return StackTraceElement.class;
      }
      public StackTraceElement demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(StackTraceElement.class, objId);
          }
          StackTraceElement entity = new StackTraceElement(java_lang_String.demarshall(obj.get("declaringClass"), a1), java_lang_String.demarshall(obj.get("methodName"), a1), java_lang_String.demarshall(obj.get("fileName"), a1), java_lang_Integer.demarshall(obj.get("lineNumber"), a1));
          a1.recordObjectHash(objId, entity);
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.StackTraceElement", t);
        }
      }
      public String marshall(StackTraceElement a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.StackTraceElement\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(640).append("{\"^EncodedType\":\"java.lang.StackTraceElement\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"fileName\" : ").append(java_lang_String.marshall(a0.getFileName(), a1)).append(",").append("\"methodName\" : ").append(java_lang_String.marshall(a0.getMethodName(), a1)).append(",").append("\"lineNumber\" : ").append(java_lang_Integer.marshall(a0.getLineNumber(), a1)).append(",").append("\"declaringClass\" : ").append(java_lang_String.marshall(a0.getClassName(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.StackTraceElement", java_lang_StackTraceElement);
    com_armandorv_cnpd_web_shared_model_ProjectInfo = new Marshaller<ProjectInfo>() {
      public Class getTypeHandled() {
        return ProjectInfo.class;
      }
      public ProjectInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ProjectInfo.class, objId);
          }
          ProjectInfo entity = new ProjectInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("managerId")) && (!obj.get("managerId").isNull())) {
            entity.setManagerId((long) java_lang_Long.demarshall(obj.get("managerId"), a1));
          }
          if ((obj.containsKey("title")) && (!obj.get("title").isNull())) {
            entity.setTitle(java_lang_String.demarshall(obj.get("title"), a1));
          }
          if ((obj.containsKey("Description")) && (!obj.get("Description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("Description"), a1));
          }
          if ((obj.containsKey("published")) && (!obj.get("published").isNull())) {
            entity.setPublished((boolean) java_lang_Boolean.demarshall(obj.get("published"), a1));
          }
          if ((obj.containsKey("url")) && (!obj.get("url").isNull())) {
            entity.setUrl(java_lang_String.demarshall(obj.get("url"), a1));
          }
          if ((obj.containsKey("lastMilestone")) && (!obj.get("lastMilestone").isNull())) {
            entity.setLastMilestone(java_lang_String.demarshall(obj.get("lastMilestone"), a1));
          }
          if ((obj.containsKey("manager")) && (!obj.get("manager").isNull())) {
            entity.setManager(java_lang_String.demarshall(obj.get("manager"), a1));
          }
          if ((obj.containsKey("knowledgeArea")) && (!obj.get("knowledgeArea").isNull())) {
            entity.setKnowledgeArea(com_armandorv_cnpd_web_shared_model_IdNameGenericInfo.demarshall(obj.get("knowledgeArea"), a1));
          }
          if ((obj.containsKey("members")) && (!obj.get("members").isNull())) {
            entity.setMembers(java_util_List.demarshall(obj.get("members"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.ProjectInfo", t);
        }
      }
      public String marshall(ProjectInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ProjectInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(1664).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ProjectInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"managerId\" : ").append(java_lang_Long.marshall(a0.getManagerId(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append(",").append("\"Description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"published\" : ").append(java_lang_Boolean.marshall(com_armandorv_cnpd_web_shared_model_ProjectInfo_published(a0), a1)).append(",").append("\"url\" : ").append(java_lang_String.marshall(a0.getUrl(), a1)).append(",").append("\"lastMilestone\" : ").append(java_lang_String.marshall(a0.getLastMilestone(), a1)).append(",").append("\"manager\" : ").append(java_lang_String.marshall(a0.getManager(), a1)).append(",").append("\"knowledgeArea\" : ").append(com_armandorv_cnpd_web_shared_model_IdNameGenericInfo.marshall(a0.getKnowledgeArea(), a1)).append(",").append("\"members\" : ").append(java_util_List.marshall(a0.getMembers(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.ProjectInfo", com_armandorv_cnpd_web_shared_model_ProjectInfo);
    java_lang_IllegalArgumentException = new Marshaller<IllegalArgumentException>() {
      public Class getTypeHandled() {
        return IllegalArgumentException.class;
      }
      public IllegalArgumentException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(IllegalArgumentException.class, objId);
          }
          IllegalArgumentException entity = new IllegalArgumentException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.IllegalArgumentException", t);
        }
      }
      public String marshall(IllegalArgumentException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.IllegalArgumentException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.IllegalArgumentException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.IllegalArgumentException", java_lang_IllegalArgumentException);
    java_lang_NegativeArraySizeException = new Marshaller<NegativeArraySizeException>() {
      public Class getTypeHandled() {
        return NegativeArraySizeException.class;
      }
      public NegativeArraySizeException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(NegativeArraySizeException.class, objId);
          }
          NegativeArraySizeException entity = new NegativeArraySizeException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.NegativeArraySizeException", t);
        }
      }
      public String marshall(NegativeArraySizeException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.NegativeArraySizeException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.NegativeArraySizeException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.NegativeArraySizeException", java_lang_NegativeArraySizeException);
    java_lang_AssertionError = new Marshaller<AssertionError>() {
      public Class getTypeHandled() {
        return AssertionError.class;
      }
      public AssertionError demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(AssertionError.class, objId);
          }
          AssertionError entity = new AssertionError(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.AssertionError", t);
        }
      }
      public String marshall(AssertionError a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.AssertionError\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.AssertionError\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.AssertionError", java_lang_AssertionError);
    java_util_ConcurrentModificationException = new Marshaller<ConcurrentModificationException>() {
      public Class getTypeHandled() {
        return ConcurrentModificationException.class;
      }
      public ConcurrentModificationException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ConcurrentModificationException.class, objId);
          }
          ConcurrentModificationException entity = new ConcurrentModificationException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.util.ConcurrentModificationException", t);
        }
      }
      public String marshall(ConcurrentModificationException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.util.ConcurrentModificationException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.util.ConcurrentModificationException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.util.ConcurrentModificationException", java_util_ConcurrentModificationException);
    com_armandorv_cnpd_web_shared_model_ContactInfo = new Marshaller<ContactInfo>() {
      public Class getTypeHandled() {
        return ContactInfo.class;
      }
      public ContactInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ContactInfo.class, objId);
          }
          ContactInfo entity = new ContactInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("lastname1")) && (!obj.get("lastname1").isNull())) {
            entity.setLastname1(java_lang_String.demarshall(obj.get("lastname1"), a1));
          }
          if ((obj.containsKey("lastname2")) && (!obj.get("lastname2").isNull())) {
            entity.setLastname2(java_lang_String.demarshall(obj.get("lastname2"), a1));
          }
          if ((obj.containsKey("fullName")) && (!obj.get("fullName").isNull())) {
            entity.setFullName(java_lang_String.demarshall(obj.get("fullName"), a1));
          }
          if ((obj.containsKey("gmailAddress")) && (!obj.get("gmailAddress").isNull())) {
            entity.setGmailAddress(java_lang_String.demarshall(obj.get("gmailAddress"), a1));
          }
          if ((obj.containsKey("isContact")) && (!obj.get("isContact").isNull())) {
            com_armandorv_cnpd_web_shared_model_ContactInfo_isContact(entity, java_lang_Boolean.demarshall(obj.get("isContact"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.ContactInfo", t);
        }
      }
      public String marshall(ContactInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ContactInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(1024).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.ContactInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"lastname1\" : ").append(java_lang_String.marshall(a0.getLastname1(), a1)).append(",").append("\"lastname2\" : ").append(java_lang_String.marshall(a0.getLastname2(), a1)).append(",").append("\"fullName\" : ").append(java_lang_String.marshall(a0.getFullName(), a1)).append(",").append("\"gmailAddress\" : ").append(java_lang_String.marshall(a0.getGmailAddress(), a1)).append(",").append("\"isContact\" : ").append(java_lang_Boolean.marshall(com_armandorv_cnpd_web_shared_model_ContactInfo_isContact(a0), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.ContactInfo", com_armandorv_cnpd_web_shared_model_ContactInfo);
    com_armandorv_cnpd_web_shared_model_NotificationInfo$Kind = new Marshaller<com.armandorv.cnpd.web.shared.model.NotificationInfo.Kind>() {
      public Class getTypeHandled() {
        return com.armandorv.cnpd.web.shared.model.NotificationInfo.Kind.class;
      }
      public com.armandorv.cnpd.web.shared.model.NotificationInfo.Kind demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          com.armandorv.cnpd.web.shared.model.NotificationInfo.Kind entity = obj != null ? Enum.valueOf(com.armandorv.cnpd.web.shared.model.NotificationInfo.Kind.class, obj.get("^EnumStringValue").isString().stringValue()) : null;
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.NotificationInfo$Kind", t);
        }
      }
      public String marshall(com.armandorv.cnpd.web.shared.model.NotificationInfo.Kind a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        return new StringBuilder(256).append(a0 != null ? new StringBuilder(64).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.NotificationInfo$Kind\",\"^EnumStringValue\":\"").append(a0.name()).append("\"}") : "null").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.NotificationInfo$Kind", com_armandorv_cnpd_web_shared_model_NotificationInfo$Kind);
    marshallers.put("com.armandorv.cnpd.web.shared.model.NotificationInfo.Kind", com_armandorv_cnpd_web_shared_model_NotificationInfo$Kind);
    com_armandorv_cnpd_web_shared_model_NotificationInfo = new Marshaller<NotificationInfo>() {
      public Class getTypeHandled() {
        return NotificationInfo.class;
      }
      public NotificationInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(NotificationInfo.class, objId);
          }
          NotificationInfo entity = new NotificationInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("message")) && (!obj.get("message").isNull())) {
            entity.setMessage(java_lang_String.demarshall(obj.get("message"), a1));
          }
          if ((obj.containsKey("object")) && (!obj.get("object").isNull())) {
            entity.setObject((long) java_lang_Long.demarshall(obj.get("object"), a1));
          }
          if ((obj.containsKey("kind")) && (!obj.get("kind").isNull())) {
            entity.setKind(obj.get("kind").isObject() != null ? Enum.valueOf(com.armandorv.cnpd.web.shared.model.NotificationInfo.Kind.class, obj.get("kind").isObject().get("^EnumStringValue").isString().stringValue()) : null);
          }
          if ((obj.containsKey("date")) && (!obj.get("date").isNull())) {
            entity.setDate((Date) java_lang_Object.demarshall(obj.get("date"), a1));
          }
          if ((obj.containsKey("isNew")) && (!obj.get("isNew").isNull())) {
            com_armandorv_cnpd_web_shared_model_NotificationInfo_isNew(entity, java_lang_Boolean.demarshall(obj.get("isNew"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.NotificationInfo", t);
        }
      }
      public String marshall(NotificationInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.NotificationInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(1024).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.NotificationInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"object\" : ").append(java_lang_Long.marshall(a0.getObject(), a1)).append(",").append("\"kind\" : ").append(a0.getKind() != null ? new StringBuilder(64).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.NotificationInfo$Kind\",\"^EnumStringValue\":\"").append(a0.getKind().name()).append("\"}") : "null").append(",").append("\"date\" : ").append(java_lang_Object.marshall(a0.getDate(), a1)).append(",").append("\"isNew\" : ").append(java_lang_Boolean.marshall(com_armandorv_cnpd_web_shared_model_NotificationInfo_isNew(a0), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.NotificationInfo", com_armandorv_cnpd_web_shared_model_NotificationInfo);
    com_armandorv_cnpd_web_shared_exception_AlreadyConnectedException = new Marshaller<AlreadyConnectedException>() {
      public Class getTypeHandled() {
        return AlreadyConnectedException.class;
      }
      public AlreadyConnectedException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(AlreadyConnectedException.class, objId);
          }
          AlreadyConnectedException entity = new AlreadyConnectedException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.AlreadyConnectedException", t);
        }
      }
      public String marshall(AlreadyConnectedException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.AlreadyConnectedException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.AlreadyConnectedException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.AlreadyConnectedException", com_armandorv_cnpd_web_shared_exception_AlreadyConnectedException);
    java_io_IOException = new Marshaller<IOException>() {
      public Class getTypeHandled() {
        return IOException.class;
      }
      public IOException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(IOException.class, objId);
          }
          IOException entity = new IOException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.io.IOException", t);
        }
      }
      public String marshall(IOException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.io.IOException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.io.IOException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.io.IOException", java_io_IOException);
    com_armandorv_cnpd_web_shared_exception_ServersideException = new Marshaller<ServersideException>() {
      public Class getTypeHandled() {
        return ServersideException.class;
      }
      public ServersideException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(ServersideException.class, objId);
          }
          ServersideException entity = new ServersideException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace(arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.exception.ServersideException", t);
        }
      }
      public String marshall(ServersideException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.ServersideException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.exception.ServersideException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.exception.ServersideException", com_armandorv_cnpd_web_shared_exception_ServersideException);
    com_armandorv_cnpd_web_shared_model_TaskInfo = new Marshaller<TaskInfo>() {
      public Class getTypeHandled() {
        return TaskInfo.class;
      }
      public TaskInfo demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(TaskInfo.class, objId);
          }
          TaskInfo entity = new TaskInfo();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId((long) java_lang_Long.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("hours")) && (!obj.get("hours").isNull())) {
            entity.setHours((int) java_lang_Integer.demarshall(obj.get("hours"), a1));
          }
          if ((obj.containsKey("workedHours")) && (!obj.get("workedHours").isNull())) {
            entity.setWorkedHours((int) java_lang_Integer.demarshall(obj.get("workedHours"), a1));
          }
          if ((obj.containsKey("start")) && (!obj.get("start").isNull())) {
            entity.setStart((Date) java_lang_Object.demarshall(obj.get("start"), a1));
          }
          if ((obj.containsKey("completed")) && (!obj.get("completed").isNull())) {
            entity.setCompleted((boolean) java_lang_Boolean.demarshall(obj.get("completed"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.TaskInfo", t);
        }
      }
      public String marshall(TaskInfo a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.TaskInfo\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(896).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.TaskInfo\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Long.marshall(a0.getId(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"hours\" : ").append(java_lang_Integer.marshall(a0.getHours(), a1)).append(",").append("\"workedHours\" : ").append(java_lang_Integer.marshall(a0.getWorkedHours(), a1)).append(",").append("\"start\" : ").append(java_lang_Object.marshall(a0.getStart(), a1)).append(",").append("\"completed\" : ").append(java_lang_Boolean.marshall(com_armandorv_cnpd_web_shared_model_TaskInfo_completed(a0), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.TaskInfo", com_armandorv_cnpd_web_shared_model_TaskInfo);
    com_armandorv_cnpd_web_shared_model_GDocsResource = new Marshaller<GDocsResource>() {
      public Class getTypeHandled() {
        return GDocsResource.class;
      }
      public GDocsResource demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObjectHash(objId)) {
            return a1.getObject(GDocsResource.class, objId);
          }
          GDocsResource entity = new GDocsResource();
          a1.recordObjectHash(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_String.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("title")) && (!obj.get("title").isNull())) {
            entity.setTitle(java_lang_String.demarshall(obj.get("title"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.armandorv.cnpd.web.shared.model.GDocsResource", t);
        }
      }
      public String marshall(GDocsResource a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObjectHash(a0)) {
          String objId = a1.getObjectHash(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.GDocsResource\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObjectHash(a0);
        a1.recordObjectHash(objId, objId);
        return new StringBuilder(384).append("{\"^EncodedType\":\"com.armandorv.cnpd.web.shared.model.GDocsResource\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_String.marshall(a0.getId(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.armandorv.cnpd.web.shared.model.GDocsResource", com_armandorv_cnpd_web_shared_model_GDocsResource);
    arrayOf_java_lang_Object_D1 = new QualifyingMarshallerWrapper(new Marshaller<Object[]>() {
      private Object[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Object[] newArray = new Object[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Object.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Object[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Object.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Object.class;
      }
      public Object[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(Object[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Object;", arrayOf_java_lang_Object_D1);
    arrayOf_java_lang_String_D1 = new QualifyingMarshallerWrapper(new Marshaller<String[]>() {
      private String[] _demarshall1(EJArray a0, MarshallingSession a1) {
        String[] newArray = new String[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_String.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(String[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_String.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return String.class;
      }
      public String[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(String[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.String;", arrayOf_java_lang_String_D1);
    arrayOf_int_D1 = new QualifyingMarshallerWrapper(new Marshaller<int[]>() {
      private int[] _demarshall1(EJArray a0, MarshallingSession a1) {
        int[] newArray = new int[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Integer.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(int[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Integer.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return int.class;
      }
      public int[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(int[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[I", arrayOf_int_D1);
    arrayOf_long_D1 = new QualifyingMarshallerWrapper(new Marshaller<long[]>() {
      private long[] _demarshall1(EJArray a0, MarshallingSession a1) {
        long[] newArray = new long[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Long.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(long[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Long.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return long.class;
      }
      public long[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(long[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[J", arrayOf_long_D1);
    arrayOf_double_D1 = new QualifyingMarshallerWrapper(new Marshaller<double[]>() {
      private double[] _demarshall1(EJArray a0, MarshallingSession a1) {
        double[] newArray = new double[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Double.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(double[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Double.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return double.class;
      }
      public double[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(double[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[D", arrayOf_double_D1);
    arrayOf_float_D1 = new QualifyingMarshallerWrapper(new Marshaller<float[]>() {
      private float[] _demarshall1(EJArray a0, MarshallingSession a1) {
        float[] newArray = new float[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Float.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(float[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Float.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return float.class;
      }
      public float[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(float[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[F", arrayOf_float_D1);
    arrayOf_short_D1 = new QualifyingMarshallerWrapper(new Marshaller<short[]>() {
      private short[] _demarshall1(EJArray a0, MarshallingSession a1) {
        short[] newArray = new short[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Short.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(short[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Short.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return short.class;
      }
      public short[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(short[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[S", arrayOf_short_D1);
    arrayOf_boolean_D1 = new QualifyingMarshallerWrapper(new Marshaller<boolean[]>() {
      private boolean[] _demarshall1(EJArray a0, MarshallingSession a1) {
        boolean[] newArray = new boolean[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Boolean.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(boolean[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Boolean.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return boolean.class;
      }
      public boolean[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(boolean[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Z", arrayOf_boolean_D1);
    arrayOf_byte_D1 = new QualifyingMarshallerWrapper(new Marshaller<byte[]>() {
      private byte[] _demarshall1(EJArray a0, MarshallingSession a1) {
        byte[] newArray = new byte[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Byte.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(byte[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Byte.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return byte.class;
      }
      public byte[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(byte[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[B", arrayOf_byte_D1);
    arrayOf_java_lang_Integer_D1 = new QualifyingMarshallerWrapper(new Marshaller<Integer[]>() {
      private Integer[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Integer[] newArray = new Integer[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Integer.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Integer[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Integer.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Integer.class;
      }
      public Integer[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(Integer[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Integer;", arrayOf_java_lang_Integer_D1);
    arrayOf_java_lang_Long_D1 = new QualifyingMarshallerWrapper(new Marshaller<Long[]>() {
      private Long[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Long[] newArray = new Long[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Long.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Long[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Long.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Long.class;
      }
      public Long[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(Long[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Long;", arrayOf_java_lang_Long_D1);
    arrayOf_java_lang_Double_D1 = new QualifyingMarshallerWrapper(new Marshaller<Double[]>() {
      private Double[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Double[] newArray = new Double[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Double.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Double[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Double.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Double.class;
      }
      public Double[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(Double[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Double;", arrayOf_java_lang_Double_D1);
    arrayOf_java_lang_Float_D1 = new QualifyingMarshallerWrapper(new Marshaller<Float[]>() {
      private Float[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Float[] newArray = new Float[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Float.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Float[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Float.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Float.class;
      }
      public Float[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(Float[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Float;", arrayOf_java_lang_Float_D1);
    arrayOf_java_lang_Short_D1 = new QualifyingMarshallerWrapper(new Marshaller<Short[]>() {
      private Short[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Short[] newArray = new Short[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Short.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Short[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Short.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Short.class;
      }
      public Short[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(Short[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Short;", arrayOf_java_lang_Short_D1);
    arrayOf_java_lang_Boolean_D1 = new QualifyingMarshallerWrapper(new Marshaller<Boolean[]>() {
      private Boolean[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Boolean[] newArray = new Boolean[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Boolean.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Boolean[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Boolean.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Boolean.class;
      }
      public Boolean[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(Boolean[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Boolean;", arrayOf_java_lang_Boolean_D1);
    arrayOf_java_lang_Byte_D1 = new QualifyingMarshallerWrapper(new Marshaller<Byte[]>() {
      private Byte[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Byte[] newArray = new Byte[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Byte.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Byte[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Byte.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Byte.class;
      }
      public Byte[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return _demarshall1(arr, a1);
        }
      }
      public String marshall(Byte[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return _marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Byte;", arrayOf_java_lang_Byte_D1);
  }

  private native static void com_armandorv_cnpd_web_shared_model_DiscussionInfo_open(DiscussionInfo instance, boolean value) /*-{
    instance.@com.armandorv.cnpd.web.shared.model.DiscussionInfo::open = value;
  }-*/;

  private native static boolean com_armandorv_cnpd_web_shared_model_DiscussionInfo_open(DiscussionInfo instance) /*-{
    return instance.@com.armandorv.cnpd.web.shared.model.DiscussionInfo::open;
  }-*/;

  private native static void com_armandorv_cnpd_web_shared_model_ResourceInfo_checked(ResourceInfo instance, boolean value) /*-{
    instance.@com.armandorv.cnpd.web.shared.model.ResourceInfo::checked = value;
  }-*/;

  private native static boolean com_armandorv_cnpd_web_shared_model_ResourceInfo_checked(ResourceInfo instance) /*-{
    return instance.@com.armandorv.cnpd.web.shared.model.ResourceInfo::checked;
  }-*/;

  private native static void com_armandorv_cnpd_web_shared_model_MessageInfo_read(MessageInfo instance, boolean value) /*-{
    instance.@com.armandorv.cnpd.web.shared.model.MessageInfo::read = value;
  }-*/;

  private native static boolean com_armandorv_cnpd_web_shared_model_MessageInfo_read(MessageInfo instance) /*-{
    return instance.@com.armandorv.cnpd.web.shared.model.MessageInfo::read;
  }-*/;

  private native static void com_armandorv_cnpd_web_shared_model_MeetingInfo_celebrated(MeetingInfo instance, boolean value) /*-{
    instance.@com.armandorv.cnpd.web.shared.model.MeetingInfo::celebrated = value;
  }-*/;

  private native static boolean com_armandorv_cnpd_web_shared_model_MeetingInfo_celebrated(MeetingInfo instance) /*-{
    return instance.@com.armandorv.cnpd.web.shared.model.MeetingInfo::celebrated;
  }-*/;

  private native static void com_armandorv_cnpd_web_shared_model_ValidationResponse_positive(ValidationResponse instance, boolean value) /*-{
    instance.@com.armandorv.cnpd.web.shared.model.ValidationResponse::positive = value;
  }-*/;

  private native static boolean com_armandorv_cnpd_web_shared_model_ValidationResponse_positive(ValidationResponse instance) /*-{
    return instance.@com.armandorv.cnpd.web.shared.model.ValidationResponse::positive;
  }-*/;

  private native static void com_armandorv_cnpd_web_shared_model_ProjectInfo_published(ProjectInfo instance, boolean value) /*-{
    instance.@com.armandorv.cnpd.web.shared.model.ProjectInfo::published = value;
  }-*/;

  private native static boolean com_armandorv_cnpd_web_shared_model_ProjectInfo_published(ProjectInfo instance) /*-{
    return instance.@com.armandorv.cnpd.web.shared.model.ProjectInfo::published;
  }-*/;

  private native static void com_armandorv_cnpd_web_shared_model_ContactInfo_isContact(ContactInfo instance, boolean value) /*-{
    instance.@com.armandorv.cnpd.web.shared.model.ContactInfo::isContact = value;
  }-*/;

  private native static boolean com_armandorv_cnpd_web_shared_model_ContactInfo_isContact(ContactInfo instance) /*-{
    return instance.@com.armandorv.cnpd.web.shared.model.ContactInfo::isContact;
  }-*/;

  private native static void com_armandorv_cnpd_web_shared_model_NotificationInfo_isNew(NotificationInfo instance, boolean value) /*-{
    instance.@com.armandorv.cnpd.web.shared.model.NotificationInfo::isNew = value;
  }-*/;

  private native static boolean com_armandorv_cnpd_web_shared_model_NotificationInfo_isNew(NotificationInfo instance) /*-{
    return instance.@com.armandorv.cnpd.web.shared.model.NotificationInfo::isNew;
  }-*/;

  private native static void com_armandorv_cnpd_web_shared_model_TaskInfo_completed(TaskInfo instance, boolean value) /*-{
    instance.@com.armandorv.cnpd.web.shared.model.TaskInfo::completed = value;
  }-*/;

  private native static boolean com_armandorv_cnpd_web_shared_model_TaskInfo_completed(TaskInfo instance) /*-{
    return instance.@com.armandorv.cnpd.web.shared.model.TaskInfo::completed;
  }-*/;

  public Marshaller getMarshaller(String a0, String a1) {
    return marshallers.get(a1);
  }
}