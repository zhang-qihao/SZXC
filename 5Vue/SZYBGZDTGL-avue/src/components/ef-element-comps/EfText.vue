<template>
  <el-form-item :class="placeholderClass" :label="label" :label-width="compLabelWidth" :prop="name" :rules="rules"
                inline-message="true">
    <span slot="label">{{ label }}</span>
    <el-input :ref="name" :size="formStyle[0].size" v-model="compValue" :placeholder="placeholder" @input="input"
              @focus="focus" @blur="blur"
              @change="change" :disabled="compReadonly" :maxlength="compMaxLength" :minlength="compMinLength">
      <div slot="append" v-if="buttonFlag">
        <slot></slot>
      </div>
    </el-input>
    <div v-if="selectList.length != 0">
      <slot name="more-select"></slot>
    </div>
  </el-form-item>
</template>

<script>
import {
  BaseFieldTag
} from './BaseFieldTag';

export default {
  name: "EfText",
  mixins: [BaseFieldTag], //继承基础BaseFieldTag
  props: {
    placeholder: { // 选择框提示文字，默认‘请选择’，非必传。
      type: String,
      default: "",
    },
    maxlength: {
      require: false,
      type: [String, Number],
      default: null
    },
    minlength: {
      require: false,
      type: [String, Number],
      default: null
    },
    buttonFlag: {
      require: false,
      type: Boolean,
      default: false,
    },
    checkFlag: {
      require: false,
      type: Boolean,
      default: false,
    },
    characterFlag: {
      //特殊字符
      require: false,
      type: Boolean,
      default: false,
    },
    numberFlag: {
      //特殊字符
      require: false,
      type: Boolean,
      default: false,
    },
    selectList: {
      require: false,
      type: Array,
      default: function () {
        return []
      },
    }
  },

  data() {
    return {
      componentType: 'EfText',
      compMaxLength: null,
      compMinLength: null,
    };
  },
  computed: {
    formStyle() {
      return this.$store.state.common.formStyle
    },
    placeholderClass() {
      return this.$store.state.common.formStyle[0].size
    }
  },
  watch: {
    value() {
      this.compValue = this.value;
    }
  },
  created() {
    if (typeof (this.maxlength) === "string") {
      this.compMaxLength = Number(this.maxlength);
    } else {
      this.compMaxLength = this.maxlength;
    }
    if (typeof (this.minlength) === "string") {
      this.compMinLength = Number(this.minlength);
    } else {
      this.compMinLength = this.minlength;
    }

  },
  methods: {
    input(val) {
      if (this.checkFlag) {
        this.compValue = val.replace(
            /[\u4e00-\u9fa5/\s+/]|[`~!@#$%^&*() \+ =<>?"{}|, \/ ;' \\ [ \] ·~！@#￥%……&*（）—— \+ ={}|《》？：“”【】、；‘’，。、]/g,
            "");
        this.$emit('compChange', this.compValue);
        this.$emit('onInput', this.compValue);
      } else if (this.characterFlag) {
        this.compValue = val.replace(
            /[`~!@#$%^&*() \+ =<>?"{}|, \/ ;' \\ [ \] ·~！@#￥%……&*（）—— \+ ={}|《》？：“”【】、；‘’，。、]/g,
            "");
        this.$emit('compChange', this.compValue);
        this.$emit('onInput', this.compValue);
      } else if (this.numberFlag) {
        var regexTel = new RegExp('^[0-9]*$');
        if (!regexTel.test(val)) {
          this.compValue = ""
        }
        this.$emit('compChange', this.compValue);
        this.$emit('onInput', this.compValue);
      } else {
        this.$emit('compChange', val);
        this.$emit('onInput', val);
      }


    },
    focus(val) {
      this.$emit('compFocus', val);
    },
    blur(val) {
      this.$emit('compBlur', val);
    },
    setFocus() {
      this.$refs[this.name].focus()
      this.$refs[this.name].select()
    },
    setBlur() {
      this.$refs[this.name].blur()
    }
  }
}
</script>

<style scoped lang="scss">

</style>
